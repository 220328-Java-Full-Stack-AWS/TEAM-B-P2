package com.revature.p2backend.beans.utilities;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * This class extends enum type which allows us to use the nullSafeSet method. This method
 * allows us to change the way our enum type is viewed in the database (where it is persisted.)
 * Instead of being viewed as the integer of the Enum it is, it displays the string of the Enum.
 */
public class EnumConverter extends EnumType {
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if(value == null){
            st.setNull(index, Types.OTHER);
        }else{
            st.setObject(index, value.toString(), Types.OTHER);
        }
    }

}
