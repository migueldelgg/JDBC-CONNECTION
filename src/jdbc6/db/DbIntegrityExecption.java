package jdbc6.db;

import java.io.Serial;

public class DbIntegrityExecption extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public DbIntegrityExecption(String msg){
        super(msg);
    }
}
