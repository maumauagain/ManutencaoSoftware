package DAO;

import Model.Activity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;


public class DbAcessActivity {
    
    // dados para acesso ao banco
    final String USUARIO = "root";
    final String SENHA = "utfpr";
    final String URL_BANCO = "jdbc:mysql://localhost:3306/gerencilar?useSSL=true&useTimezone=true&serverTimezone=UTC";
    
    // constantes de acesso
    final String CLASSE_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    // comandos
	final String INSERIR = "INSERT INTO Activity(act_name, act_description, act_start_date, act_end_date, act_status, user_id) VALUES(?, ?, ?, ?, ?, ?)";
	final String ATUALIZAR = "UPDATE Activity SET act_name=?, act_description=?, act_start_date=?, act_end_date=?, user_id=?, act_status=? WHERE act_id = ?";
	final String BUSCAR = "SELECT act_name, act_description, act_start_date, act_end_date, act_status, user_id  FROM User WHERE act_id = ?";
	final String BUSCAR_TODOS = "SELECT act_id, act_name, act_description, act_start_date, act_end_date, act_status, user_name, a.user_id from activity a, user u where a.user_id = u.user_id";
	final String APAGAR = "DELETE FROM Activity WHERE act_id = ?";
        
        final String FORMATO_DATA = "yyyy/MM/dd";
        final SimpleDateFormat FORMATADOR = new SimpleDateFormat(FORMATO_DATA);
        
        public void salvar(Activity atividade) {
            try {
		Connection con = conexao();
		PreparedStatement salvar = con.prepareStatement(INSERIR);
                String dateStr = FORMATADOR.format(atividade.getStartDate());
                String dateStr2 = FORMATADOR.format(atividade.getEndDate());
		salvar.setString(1, atividade.getName());
		salvar.setString(2, atividade.getDescription());
                salvar.setString(3, dateStr);
                salvar.setString(4, dateStr2);
                salvar.setInt(5, atividade.getStatus());
                salvar.setInt(6, atividade.getUserId());
		salvar.executeUpdate();
		salvar.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
		System.err.println("ERROR SALVANDO ATIVIDADE");
		System.exit(0);
		} 
	}
        
        public void atualizar(Activity activity) {
		try {
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(ATUALIZAR);
			atualizar.setString(1, activity.getName());
			atualizar.setString(2, activity.getDescription());
			atualizar.setString(3, (FORMATADOR.format(activity.getStartDate())));
                        atualizar.setString(4, (FORMATADOR.format(activity.getEndDate())));
                        atualizar.setInt(5, activity.getUserId());
                        atualizar.setInt(6, activity.getStatus());
                        atualizar.setInt(7, activity.getId());
			atualizar.executeUpdate();
			atualizar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR ATUALIZANDO ATIVIDADE COM ID " + activity.getId());
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
		System.err.println("ERROR APAGANDO ATIVIDADE COM ID " + id);
		System.exit(0);
		} 
	}
        
        public List<Activity> buscarTodas() {
		List<Activity> atividades = new ArrayList<>();
		try {
			Connection con = conexao();
			PreparedStatement buscarTodos = con.prepareStatement(BUSCAR_TODOS);
			ResultSet resultadoBusca = buscarTodos.executeQuery();
			while (resultadoBusca.next()) {
				Activity activity = extraiActivity(resultadoBusca);
				atividades.add(activity);
			}
			buscarTodos.close();
			con.close(); 
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR BUSCANDO TODAS AS ATIVIDADES.");
			System.exit(0);
		} 
		return atividades;
	}
        
        private Activity extraiActivity(ResultSet resultadoBusca) throws SQLException, NumberFormatException {
		Activity activity = new Activity();
                activity.setId(resultadoBusca.getInt(1));
		activity.setName(resultadoBusca.getString(2));
                activity.setDescription(resultadoBusca.getString(3));
                activity.setStartDate(resultadoBusca.getDate(4));
		activity.setEndDate(resultadoBusca.getDate(5));
		activity.setStatus(resultadoBusca.getInt(6));              
                activity.setUserName(resultadoBusca.getString(7));
                activity.setUserId(resultadoBusca.getInt(8));
                                              
		return activity;
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
