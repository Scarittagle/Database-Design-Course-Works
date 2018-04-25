from flask import Flask, request, Response, json, jsonify, render_template, flash, redirect, url_for
from urllib.parse import unquote
from flask_login import current_user, login_user, logout_user, login_required
from flask_login import UserMixin
from app.models import Customer, Order, Part
from werkzeug.urls import url_parse
from sqlalchemy import text
from werkzeug.security import generate_password_hash, check_password_hash
import jinja2
import json

from app import app, db
from app.forms import LoginForm, RegistrationForm, SectionForm, LoadPartForm, SearchByBrandForm, SearchByStkNoForm, OrderForm


@app.route('/')
@app.route('/index')
@login_required
def index():
    user = {'username': 'Rohan'}
    posts = [
        {
            'author': {'username': 'John'},
            'body': 'Beautiful day in Portland!'
        },
        {
            'author': {'username': 'Susan'},
            'body': 'The Avengers movie was so cool!'
        }
    ]
    return render_template('homepage.html', title='Home', posts=posts)


@app.route('/login', methods=['GET', 'POST'])
def login():
    if current_user.is_authenticated:
        return redirect(url_for('index'))
    form = LoginForm()
    con = db.engine.connect()
    if form.validate_on_submit():
        user = con.execute('SELECT * FROM Customer WHERE username=="{0}"'.format(form.username.data))
        theUser = user.fetchone()
        if not theUser:
            flash("Invalid username or password")
            return redirect(url_for('login'))
        if not check_password_hash(theUser[3],form.password.data):
            flash("Invalid customername or password")
            return redirect(url_for('login'))
        loginUser = Customer.query.filter_by(username=theUser[1]).first()
        login_user(loginUser,remember=form.remember_me.data)
        next_page = request.args.get('next')
        if not next_page or url_parse(next_page).netloc != '':
            next_page = url_for('index')
        return redirect(next_page)
    return render_template('login.html', title='Sign In', form=form)


@app.route('/logout')
def logout():
    logout_user()
    return redirect(url_for('index'))



@app.route('/register', methods=['GET', 'POST'])
def register():
    if current_user.is_authenticated:
        return redirect(url_for('index'))
    form = RegistrationForm()
    con = db.engine.connect()
    if form.validate_on_submit():
        '''user = User(username=form.username.data, email=form.email.data)'''
        '''user.set_password(form.password.data)'''
        username=form.username.data
        email=form.email.data
        password_hash=generate_password_hash(form.password.data)
        '''db.session.add(user)'''
        con.execute('INSERT INTO Customer (username,email,password_hash) VALUES ("{0}","{1}","{2}")'.format(username,email,password_hash))
        db.session.commit()
        flash('Congratulations, you are now a registered customer!')
        return redirect(url_for('login'))
    return render_template('register.html', title='Register', form=form)

@app.route('/load',methods=['GET','POST'])
def load():
    '''
    Only owner of this system will be allowed to add 
    new parts into the database
    '''
    if int(current_user.get_id()) is not 1:
        print('You do not have the authority!')
        return redirect(url_for('index'))
    form = LoadPartForm()
    con = db.engine.connect()
    if form.validate_on_submit():
        stocknumber = form.stocknumber.data
        brand = form.brand.data
        category = form.category.data
        price = form.price.data
        part = con.execute('SELECT * FROM Part WHERE stocknumber={0}'.format(stocknumber)).fetchone()
        if part:
            number = part[4]
            stock_number = part[0]
            con.execute('UPDATE Part SET quantity={0} WHERE stocknumber={1}'.format(number+1,stock_number))
        else:
            con.execute('INSERT INTO Part (stocknumber,brand,category,price,quantity) VALUES ({0},"{1}","{2}",{3},{4})'.format(stocknumber,brand,category,price,1))
            db.session.commit()
        print(part)
        print(current_user.get_id())
        return redirect(url_for('load'))
    return render_template('LoadPart.html',title='Load',form=form)

@app.route('/view', methods=['GET','POST'])
def view():
    stuff = []
    if current_user.is_authenticated:
        con = db.engine.connect()
        parts = con.execute('SELECT * FROM Part')
        for part in parts:
            stuff.append(part)
        return render_template('ViewParts.html',Stuff=stuff,title='View All Parts')
    return render_template('ViewParts.html',Stuff=stuff,title='View All Parts')


@app.route('/searchbystkno', methods=['GET','POST'])
def searchByStkNo():
    form = SearchByStkNoForm()
    stuff = []
    if current_user.is_authenticated:
        con = db.engine.connect()
        if form.validate_on_submit():
            stockNumber = form.stocknumber.data
            parts = con.execute('SELECT * FROM Part WHERE stocknumber={0}'.format(stockNumber))
            for part in parts:
                stuff.append(part)
            return render_template('SearchByStkNo.html',Stuff=stuff,title='Search By Stock No.',form=form)
    return render_template('SearchByStkNo.html',Stuff=stuff,title='Search By Stock No.',form=form)


@app.route('/searchbybrand', methods=['GET','POST'])
def searchByBrand():
    form = SearchByBrandForm()
    stuff = []
    if current_user.is_authenticated:
        con = db.engine.connect()
        if form.validate_on_submit():
            brand = form.brand.data
            parts = con.execute('SELECT * FROM Part WHERE brand="{0}"'.format(brand))
            for part in parts:
                stuff.append(part)
            return render_template('SearchByBrand.html',Stuff=stuff,title='Search By Brand',form=form)
    return render_template('SearchByBrand.html',Stuff=stuff,title='Search By Brand',form=form)


@app.route('/order', methods=['GET','POST'])
def order():
    form = OrderForm()
    outputInfo = ''
    if current_user.is_authenticated:
        if int(current_user.get_id()) is 1:
            return redirect(url_for('index'))
        con = db.engine.connect()
        if form.validate_on_submit():
            userid = int(current_user.get_id())
            stockNumber = form.stocknumber.data
            part = con.execute('SELECT * FROM Part WHERE stocknumber = {0}'.format(stockNumber)).fetchone()
            if not part:
                outputInfo = 'No part matches the stock number you typed in.'
                return render_template('Order.html',Info=outputInfo,title='Order Info',form=form)
            else:
                brand = part[1]
                category = part[2]
                price = part[3]
                originQty = part[4]
                if (originQty > 1):
                    con.execute('UPDATE Part SET quantity = {0} WHERE stocknumber = {1}'.format((originQty-1),stockNumber))
                else:
                    con.execute('DELETE FROM Part WHERE stocknumber = {0}'.format(stockNumber))
                sql_section = 'INSERT INTO [Order] (customer_id, part_id, brand, category, price) VALUES ({0}, {1}, "{2}", "{3}", {4})'.format(userid,stockNumber,brand,category,price)
                con.execute(sql_section)
                db.session.commit()
                outputInfo = 'Successfully Ordered!'
                return render_template('Order.html',Info=outputInfo,title='Order Info',form=form)
    return render_template('Order.html',Info=outputInfo,title='Order Info',form=form)

@app.route('/showOrder', methods=['GET','POST'])
def showOrder():
    if current_user.is_authenticated:
        con = db.engine.connect()
        total = 0
        stuffs = con.execute('SELECT * FROM [Order] WHERE customer_id={0}'.format(int(current_user.get_id()))).fetchall()
        if stuffs:
            for stuff in stuffs:
                total = total + stuff[5]
        else:
            stuffs = 'You do not have any order'
        return render_template('ShowOrder.html',Stuff=stuffs,Price=total,title='Show Order')
    return render_template('ShowOrder.html',Stuff=stuffs,Price=total,title='Show Order')


@app.route('/pay',methods=['GET','POST'])
def pay():
    if current_user.is_authenticated:
        con = db.engine.connect()
        con.execute('DELETE FROM [Order] WHERE customer_id={0}'.format(int(current_user.get_id())))
        return render_template('PaySuccess.html')
    return render_template('PaySuccess.html')
