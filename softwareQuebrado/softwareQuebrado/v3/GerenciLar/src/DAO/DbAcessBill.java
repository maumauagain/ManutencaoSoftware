package DAO;

import Model.Bill;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DbAcessBill {

    // dados para acesso ao banco
    final String USUARIO = "root";
    final String SENHA = "utfpr";
    final String URL_BANCO = "jdbc:mysql://localhost:3306/gerencilar?useSSL=true&useTimezone=true&serverTimezone=UTC";

    // constantes de acesso
    final String CLASSE_DRIVER = "com.mysql.cj.jdbc.Driver";

    // comandos
    final String INSERIR = "INSERT INTO Bill(bill_name, bill_exp_date, bill_status, bill_value) VALUES(?, ?, ?, ?)";
    final String ATUALIZAR = "UPDATE Bill SET bill_name=?, bill_exp_date=?, bill_status=?, bill_value=?  WHERE bill_id = ?";
    final String BUSCAR = "SELECT bill_name, bill_exp_date, bill_status, bill_value  FROM Bill WHERE bill_id = ?";
    final String BUSCAR_TODOS = "SELECT bill_id, bill_name, bill_exp_date, bill_status, bill_value FROM Bill";
    final String APAGAR = "DELETE FROM Bill WHERE bill_id = ?";

    final String FORMATO_DATA = "yyyy/MM/dd";
    final SimpleDateFormat FORMATADOR = new SimpleDateFormat(FORMATO_DATA);

    public void atualizar(Bill bill) {
        try {
            Connection con = conexao();
            PreparedStatement atualizar = con.prepareStatement(ATUALIZAR);
            atualizar.setString(1, bill.getName());
            atualizar.setString(2, (FORMATADOR.format(bill.getExpDate())));
            atualizar.setInt(3, 0);
            atualizar.setFloat(4, bill.getValue());
            atualizar.setInt(5, bill.getId());
            atualizar.executeUpdate();
            atualizar.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR ATUALIZANDO CONTA COM ID " + bill.getId());
            System.exit(0);
        }

    }

    public void apagar(int id) {
        try {
            Connection con = conexao();
            PreparedStatement apagar = con.prepareStatement(APAGAR);
            apagar.setInt(1, id);
            apagar.executeUpdate();
            apagar.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR APAGANDO CONTA COM ID " + id);
            System.exit(0);
        }
    }

    public List<Bill> buscarTodas() {
        List<Bill> bills = new ArrayList<>();
        try {
            Connection con = conexao();
            PreparedStatement buscarTodos = con.prepareStatement(BUSCAR_TODOS);
            ResultSet resultadoBusca = buscarTodos.executeQuery();
            while (resultadoBusca.next()) {
                Bill bill = extraiBill(resultadoBusca);
                bills.add(bill);
            }
            buscarTodos.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR BUSCANDO TODAS AS CONTAS.");
            System.exit(0);
        }
        return bills;
    }

    private Bill extraiBill(ResultSet resultadoBusca) throws SQLException, NumberFormatException {
        Bill bill = new Bill();
        bill.setId(resultadoBusca.getInt(1));
        bill.setName(resultadoBusca.getString(2));
        bill.setExpDate(resultadoBusca.getDate(3));
        bill.setStatus(resultadoBusca.getInt(4));
        bill.setValue(resultadoBusca.getFloat(5));

        return bill;
    }

    private Connection conexao() {
        try {
            Class.forName(CLASSE_DRIVER);
            return DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ClassNotFoundException) {
                System.err.println("VERIFIQUE SE O DRIVER DO BANCO DE DADOS ESTÁ NO CLASSPATH");
            } else {
                System.err.println("VERIFIQUE SE O BANCO ESTÁ RODANDO E SE OS DADOS DE CONEXÃO ESTÃO CORRETOS");
            }

            System.exit(0);
            // o sistema deverá sair antes de chegar aqui...
            return null;
        }
    }
}
