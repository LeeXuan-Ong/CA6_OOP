package com.dkit.LeeXuanOng.SD2A.DAOException;

import java.sql.SQLException;

public class DAOException extends SQLException{
    /**     Feb 2022
     * A 'homemade' Exception to report exceptions
     *  arising in the Data Access Layer.
     */

        public DAOException()
        {
            // not used
        }

        public DAOException(String aMessage)
        {

            super(aMessage);
        }


}
