package com.ggingenieria.estacion.dbf;

import com.idataconnect.jdbfdriver.DBF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by francisco on 13/07/15.
 */
@Component("dbfSurtidor")
public class DbfSurtidor {

    @Value("${dbf.file}")
    private String dbfFile;
    private DBF db = null;

    public DbfSurtidor()  {
    }

    public Map<String,Double> getLecturaSurtidores() throws IOException {

        if(db == null){
            db = DBF.use(dbfFile);
        }
        db.gotoRecord(0);
        Map<String,Double> surtidores = new HashMap<>();
        while (!db.eof()) {
            if(!db.deleted()) {
                surtidores.put(db.getString("SURTIDOR"), db.getDouble("LITROS"));
            }
            db.skip();
        }
        return surtidores;
    }

}
