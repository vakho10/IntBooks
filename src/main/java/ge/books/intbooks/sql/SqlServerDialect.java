package ge.books.intbooks.sql;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;

public class SqlServerDialect extends SQLServerDialect {

	public SqlServerDialect() {
		super();
		registerColumnType(Types.CHAR, "nchar(1)");
		registerColumnType(Types.VARCHAR, "nvarchar($l)");
	}
}
