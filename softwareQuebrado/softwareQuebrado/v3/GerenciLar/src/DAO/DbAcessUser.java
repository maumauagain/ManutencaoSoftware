package DAO;

import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DbAcessUser {
    
    // dados para acesso ao banco
    final String USUARIO = "root";
    final String SENHA = "utfpr";
    final String URL_BANCO = "jdbc:mysql://localhost:3306/gerencilar?useSSL=true&useTimezone=true&serverTimezone=UTC";
    
    // constantes de acesso
    final String CLASSE_DRIVER = "com.mysql.jdbc.Driver";
    
    // comandos
	final String INSERIR = "INSERT INTO User(user_name, user_password, user_rg, user_email, user_phone, user_type_id) VALUES(?, ?, ?, ?, ?, ?)";
	final String ATUALIZAR = "UPDATE User SET user_name=?, user_password=?, user_rg=?, user_email=?, user_phone=?, user_type_id=? WHERE user_id = ?";
	final String BUSCAR = "SELECT user_name, user_email, user_phone, user_rg  FROM User WHERE user_id = ?";
	final String BUSCAR_TODOS = "SELECT user_id, user_name, user_email, user_phone, user_rg, user_password, user_type_id FROM User";
	final String APAGAR = "DELETE FROM User WHERE user_id = ?";
        final String BUSCAR_NOME  = "SELECT user_name FROM User";
        final String VALIDA_LOGIN = "SELECT user_email, user_password FROM User WHERE user_email=? and user_password=?";   
        
        public void salvar(User usuario) {
            try {
		Connection con = conexao();
		PreparedStatement salvar = con.prepareStatement(INSERIR);
		salvar.setString(1, usuario.getName());
		salvar.setString(2, usuario.getPassword());
                salvar.setInt(3, usuario.getRg());
                salvar.setString(4, usuario.getEmail());
                salvar.setString(5, usuario.getPhone());
                salvar.setInt(6, usuario.getUserType());
		salvar.executeUpdate();
		salvar.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
		System.err.println("ERROR SALVANDO CONTA");
		System.exit(0);
		} 
	}
        
        public void atualizar(User usuario) {
		try {
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(ATUALIZAR);
			atualizar.setString(1, usuario.getName());
			atualizar.setString(2, usuario.getPassword());
			atualizar.setInt(3, usuario.getRg());
                        atualizar.setString(4, usuario.getEmail());
                        atualizar.setString(5, usuario.getPhone());
                        atualizar.setInt(6, usuario.getUserType());
                        atualizar.setInt(7, usuario.getId());
			atualizar.executeUpdate();
			atualizar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR ATUALIZANDO CONTA COM ID " + usuario.getId());
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
        
        public List<User> buscarTodas() {
		List<User> users = new ArrayList<>();
		try {
			Connection con = conexao();
			PreparedStatement buscarTodos = con.prepareStatement(BUSCAR_TODOS);
			ResultSet resultadoBusca = buscarTodos.executeQuery();
			while (resultadoBusca.next()) {
				User user = extraiUser(resultadoBusca);
				users.add(user);
			}
			buscarTodos.close();
			con.close(); 
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR BUSCANDO TODAS AS CONTAS.");
			System.exit(0);
		} 
		return users;
	}
        
        public List<String> buscarNome(){
            List<String> users = new ArrayList<>();
            try {
                Connection con = conexao();
                PreparedStatement buscarName = con.prepareStatement(BUSCAR_NOME);
                ResultSet resultadoBusca = buscarName.executeQuery();
                while(resultadoBusca.next()){
                    users.add(resultadoBusca.getString("user_name"));
                }
                buscarName.close();
                con.close();
            } catch (Exception e){
                e.printStackTrace();
                System.err.println("ERROR BUSCANDO NOMES DE USUARIO.");
                System.exit(0);
            }
            return users;
        }
        
        private User extraiUser(ResultSet resultadoBusca) throws SQLException, NumberFormatException {
		User user = new User();
                user.setId(resultadoBusca.getInt(1));
		user.setName(resultadoBusca.getString(2));
                user.setEmail(resultadoBusca.getString(3));
                user.setPhone(resultadoBusca.getString(4));
		user.setRg(resultadoBusca.getInt(5));
                user.setPassword(resultadoBusca.getString(6));
                user.setUserType(resultadoBusca.getInt(7));
                               
		return user;
	}       
        
        public boolean autenticaLogin(String login, String senha){
            boolean autenticado = false;
            
            try {
		Connection con = conexao();
		PreparedStatement ps = con.prepareStatement(VALIDA_LOGIN);
                ps.setString(1, login);
                ps.setString(2, senha);                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next())
                    autenticado = true;
                else {
                    ps.close();
                    return autenticado;
                }
            }
            catch(SQLException ex){
                return false;
            }
            
            return autenticado;
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
