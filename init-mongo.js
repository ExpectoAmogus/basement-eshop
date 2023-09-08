db.createUser(
    {
        user: "mongo",
        pwd: "admin",
        roles: [
            {
                role: "readWrite",
                db: "cart_service"
            }
        ]
    }
)