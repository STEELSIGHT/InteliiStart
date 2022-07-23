# Explore Rest APIs
| Method | Url                                                |                                             | Request Body |
|:------:|:--------------------------------------------------:|:-------------------------------------------:|:------------:|
| POST   | /add_user                                          | Add user (lastname,firstname,amountOfMoney) | JSON         |
| POST   | /add_product                                       | Add product(name,price)                     | JSON         |
| GET    | /all_users                                         | Get all users in database                   |              |
| GET    | /all_product                                       | Get all product in database                 |              |
| GET    | /buy_product?userId={userId}&productId={productId} | Buy product with id by user id              |              |
| GET    | /all_productlist                                   | Get all purchased                           |              |
| GET    | /all_product_for_user?userId={userId}              | Get all purchased user products             |              |
| GET    | /all_user_for_product?productId={productId}        | Get all users for product                   |              |
| GET    | /delete_user?userId={userId}                       | Detete user by id                           |              |
| GET    | /delete_product?productId={productId}              | Delete product by id                        |              |
