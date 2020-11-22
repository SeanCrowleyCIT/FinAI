from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, PasswordField
from wtforms.validators import InputRequired, DataRequired, Length, Email, EqualTo
from wtforms.fields.html5 import EmailField

class MyForm(FlaskForm):
    email = StringField('email',
                           validators=[DataRequired(), Email(message=None, granular_message=False, check_deliverability=False, allow_smtputf8=True, allow_empty_local=False) ])
  
# password = StringField('password',
#                           validators=[DataRequired()])
#    passwordConfirm = StringField('passwordConfirm',
#                           validators=[DataRequired()])'''

    password = PasswordField('password', 
			  [InputRequired(), EqualTo('passwordConfirm', message='Passwords must match')])
    passwordConfirm  = PasswordField('passwordConfirm')
    submit = SubmitField('submit')
