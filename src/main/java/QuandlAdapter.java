import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.Row;
import com.jimmoores.quandl.classic.ClassicQuandlSession;

import java.time.format.DateTimeFormatter;

public class QuandlAdapter implements ServicoCotacao {
    @Override
    public double getCotacao(String codEmpresa) {
        System.out.printf("Cotação da Empresa %s pelo serviço Quandl: http://quandl.com/%n", codEmpresa);
        var session = ClassicQuandlSession.create();
        var request = DataSetRequest.Builder
                .of(codEmpresa)
                .withMaxRows(1)
                .build();
        var tabularResult = session.getDataSet(request);
        if (tabularResult.size() == 0)
            throw new RuntimeException("Não foi possível obter a cotação para a empresa " + codEmpresa);

        Row row = tabularResult.get(0);
        return row.getDouble("Close");
    }
}
