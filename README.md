# money transfer project api
# Technologies :

-Java

-Log4j

-Jetty Server

-H2 Database

-Jax-rs

-Quartz


# User Services :


localhost:3020/user/allUser

localhost:3020/user/userByPhoneNumber/{phoneNumber}

localhost:3020/user/createUser

localhost:3020/user/updateUser

localhost:3020/user/deleteUser/{phoneNumber}

# User Parameter :

    {
        "name": "muhammed",
        "surname": "baglar",
        "phoneNumber": "05439004050",
        "emailAdress": "muhammedbaglar@gmail.com"
    }

# Account Services  :

localhost:3020/account/allAccounts

localhost:3020/account/createAccount

localhost:3020/account/updateAccount

localhost:3020/account/deleteAccount/{accountNumber}

localhost:3020/account/accountsByAccountNumber/{accountNumber}

localhost:3020/account/accountsByPhoneNumber/{phoneNumber}

# Acount Parameter :

    {
        "accountNumber": 252502102,
        "phoneNumber": "05439004050",
        "balance": 57.343,
        "currencyCode": "TRY"
    },


# Transactional Services :

localhost:3020/transaction/sendMoney

localhost:3020/transaction/transactionBySenderAccount/{senderAccount}

localhost:3020/transaction/allTransaction

localhost:3020/transaction/transactionByReceiverAccount/{receiverAccount}

localhost:3020/transaction/notCompletedTransaction

localhost:3020/transaction/acceptMoney

# Transaction Parameter :

# For Send Money  Parameter :

    {
        "senderAccount": 252502102,
        "receiverAccount": 432345765,
        "amount": 23.000,
        "senderCurrencyCode": "TRY",
        "receiverCurrencyCode": "TRY",
    }

# For LastStatus of Transaction Parameter :
    
		{
        "senderAccount": 252502102,
        "receiverAccount": 432345765,
        "startDate": "2019-11-27",
        "endDate": "2019-12-01",
        "amount": 23.000,
        "senderCurrencyCode": "TRY",
        "receiverCurrencyCode": "TRY",
        "statu": "A",
        "isReverse": 0,
        "transactionToken": "ununjzgvvs"
    }
