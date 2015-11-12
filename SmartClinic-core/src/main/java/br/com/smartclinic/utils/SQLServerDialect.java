package br.com.smartclinic.utils;

import java.sql.Types;

public class SQLServerDialect extends org.hibernate.dialect.SQLServerDialect {
	
    public SQLServerDialect() {
        registerColumnType(Types.BOOLEAN, "tinyint");
    }
}