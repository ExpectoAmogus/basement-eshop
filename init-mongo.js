print('Start creating database ##########################')
db = db.getSiblingDB('cart_service');
db.createUser(
    {
        user: 'mongo',
        pwd:  'admin',
        roles: [{role: 'readWrite', db: 'cart_service'}],
    }
);
db = new Mongo().getDB("cart_service");
db.createCollection('carts');
db.createCollection('cart_items');

db = db.getSiblingDB('product_query_service');
db.createUser(
    {
        user: 'mongo',
        pwd:  'admin',
        roles: [{role: 'readWrite', db: 'product_query_service'}],
    }
);
db = new Mongo().getDB("product_query_service");
db.createCollection('products');
db.createCollection('categories');

db = db.getSiblingDB('product_command_service');
db.createUser(
    {
        user: 'mongo',
        pwd:  'admin',
        roles: [{role: 'readWrite', db: 'product_command_service'}],
    }
);
db = new Mongo().getDB("product_command_service");
db.createCollection('products');
db.createCollection('categories');
print('End creating database ##########################')