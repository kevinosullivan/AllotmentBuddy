package com.thesullies.allotment.jpa.repositories;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * Fix Hibernate bug handling boolean / tiny int. This class is referenced in jpa.xml, instead of thedefault MySQL5Dialect
 * <p>
 * See <a
 * href="http://stackoverflow.com/a/8889257/156477">http://stackoverflow.com
 * /a/8889257/156477</a> and <a
 * href="https://hibernate.atlassian.net/browse/HHH-6935"
 * >https://hibernate.atlassian.net/browse/HHH-6935</a>
 * </p>
 * 
 * @author kevinosullivan
 * 
 */
public class Mysql5BitBooleanDialect extends MySQL5Dialect {
   public Mysql5BitBooleanDialect() {
      super();
      registerColumnType(java.sql.Types.BOOLEAN, "bit");
   }
}
