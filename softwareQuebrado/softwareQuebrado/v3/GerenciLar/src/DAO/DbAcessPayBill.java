package DAO;

import Model.Bill;
import Model.PayBill;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbAcessPayBill {
    // dados para acesso ao banco
    final String USUARIO = "root";
    final String SENHA = "utfpr";
    final String URL_BANCO = "jdbc:mysql://localhost:3306/gerencilar?useSSL=true&useTimezone=true&serverTimezone=UTC";
    
    // constantes de acesso
    final String CLASSE_DRIVER = "com.mysql.jdbc.Driver";
    
    // comandos
	final String INSERIR = "INSERT INTO User_has_Bill(user_id, bill_id, user_has_bill_status) VALUES(?, ?, ?)";
        final String BUSCAR_TODOS = "select u.user_name, c.bill_name, user_has_bill_status from user_has_bill d, user u, bill c where d.user_id = u.user_id and d.bill_id = c.bill_id";
        
        public void salvar(PayBill pb) {
            try {
		Connection con = conexao();
		PreparedStatement salvar = con.prepareStatement(INSERIR);
		salvar.setInt(1, pb.getIdUser());
		salvar.setInt(2, pb.getIdBill());
                salvar.setInt(3, pb.getStatus());
		salvar.executeUpdate();
		salvar.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
		System.err.println("ERROR SALVANDO CONTA");
		System.exit(0);
		} 
	}
        
        public List<PayBill> buscarTodas() {
		List<PayBill> pbs = new ArrayList<>();
		try {
			Connection con = conexao();
			PreparedStatement buscarTodos = con.prepareStatement(BUSCAR_TODOS);
			ResultSet resultadoBusca = buscarTodos.executeQuery();
			while (resultadoBusca.next()) {
				PayBill pb = extraiBill(resultadoBusca);
				pbs.add(pb);
			}
			buscarTodos.close();
			con.close(); 
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR BUSCANDO TODAS AS CONTAS.");
			System.exit(0);
		} 
		return pbs;
	}
                
        private PayBill extraiBill(ResultSet resultadoBusca) throws SQLException, NumberFormatException {
		PayBill pb = new PayBill();
		pb.setNameUser(resultadoBusca.getString(1));
                pb.setNameBill(resultadoBusca.getString(2));
                pb.setStatus(resultadoBusca.getInt(3));

		return pb;
	}       
                
        private Connection conexao() {
            try {
                Class.forName(CLASSE_DRIVER);
                return DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
            } catch (Exception e) {
                e.printStackTrace();
                if(e instanceof ClassNotFoundException)
                    System.err.println("VERIFIQUE SE O DRIVER DO BANCO DE DADOS ESTÁ NO CLASSPATH");
                 else 
                    System.err.println("VERIFIQUE SE O BANCO ESTÁ RODANDO E SE OS DADOS DE CONEXÃO ESTÃO CORRETOS");

                System.exit(0);
                // o sistema deverá sair antes de chegar aqui...
                return null;
            }
	}
}