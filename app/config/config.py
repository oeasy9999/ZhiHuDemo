#-*- coding:utf8 -*-
import os
import logging
import configparser
from flask_mysqldb import MySQL
from datetime import timedelta
basedir = os.path.abspath(os.path.dirname(__file__))

if not os.path.exists(os.path.join(basedir,'logs','api')):
    os.makedirs(os.path.join(basedir,'logs','api'))

if not os.path.exists(os.path.join(basedir,'logs','flask')):
    os.makedirs(os.path.join(basedir,'logs','flask'))

SECRET_KEY = os.environ.get('SECRET_KEY') or 'hard to guess string'
SSL_DISABLE = True
SQLALCHEMY_COMMIT_ON_TEARDOWN = True
SQLALCHEMY_RECORD_QUERIES = True


LOGGING = {
    'version': 1,
    'disable_existing_loggers': True,
    'formatters': {
        'standard': {
            'format': '%(asctime)s [%(name)s:%(lineno)d] [%(pathname)s-%(funcName)s] [%(levelname)s]- %(message)s'
        },
        'user':{
            'format': '%(asctime)s [%(name)s:%(lineno)d] [%(pathname)s-%(funcName)s] [%(levelname)s]- %(message)s'
        }
    },
    'filters': {
    },
    'handlers': {
        'request': {
            'level':'INFO',
            'class':'cloghandler.ConcurrentRotatingFileHandler',
            'filename': os.path.join(basedir,'logs','flask','flask_system.log'),
            'maxBytes': 1024*1024*5,
            'delay': 10,         
            'formatter':'standard',
            'backupCount':60,
            # 'encoding':'utf-8',
        },
        'b_log':{
            'level':'DEBUG',
            'class':'cloghandler.ConcurrentRotatingFileHandler',
            'filename': os.path.join(basedir,'logs','api','api.log'),
            'maxBytes': 1024*1024*5,
            'delay': 10,
            'formatter':'user',
            'backupCount':60,
            # 'encoding':'utf-8',
        }
    },
    'loggers': {
        'request': {
            'handlers': ['request'],
            'level': 'DEBUG',
            'propagate': False
        },
        'b_log':{
            'handlers':['b_log'],
            'level':'DEBUG',
            'propagate':False
        },

    }

}

import logging.config
from logging.config import dictConfig
logging.config.dictConfig(LOGGING)

request_log = logging.getLogger('request')
b_log = logging.getLogger('b_log')

# MYSQL
dc = configparser.ConfigParser()
dc.read('./avatar')    
BUSS_ERRCODE = dc['ERRCODE']

SQLALCHEMY_DATABASE_URI = 'mysql://{}:{}@{}/{}'.format(
    dc['DEFAULT']['USERNAME'], dc['DEFAULT']['PASSWORD'], dc['DEFAULT']['HOSTNAME'], dc['DEFAULT']['DATABASE']
)

SQLALCHEMY_DATABASE_URI = SQLALCHEMY_DATABASE_URI
SQLALCHEMY_TRACK_MODIFICATIONS = True

SQLALCHEMY_BINDS = {
    'ken': 'mysql://{}:{}@{}/{}'.format(dc['DEFAULT']['USERNAME'], dc['DEFAULT']['PASSWORD'], dc['DEFAULT']['HOSTNAME'], dc['DEFAULT']['DATABASE']),
    'sale': 'mysql://{}:{}@{}/{}'.format(dc['SALE']['USERNAME'], dc['SALE']['PASSWORD'], dc['SALE']['HOSTNAME'], dc['SALE']['DATABASE'])
}

TOKEN_EXP=60000
# config = {
#     #首先找这个配置
#     'development': DevelopmentConfig,
#     'production': ProductionConfig,
#     'default': DevelopmentConfig,
# }

CELERY_BROKER_URL = 'redis://localhost:6379/0'
CELERY_RESULT_BACKEND = 'redis://localhost:6379/0'
CELERYBEAT_SCHEDULE = {
    'import_data': {
        'task': 'import_data',
        'schedule': timedelta(seconds=10)
    },
}


