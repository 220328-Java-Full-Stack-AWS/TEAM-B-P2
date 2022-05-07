package com.revature.p2backend.utilities;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionManager {

private Session session;

    public TransactionManager(Session session) {
        this.session = session;
    }


public Transaction beginTrasaction(){
        return session.beginTransaction();
}

public void commitTransaction(Transaction transaction){
        transaction.commit();
}

}
