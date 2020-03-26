package Pais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD {
	
	static {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		} catch (ClassNotFoundException e) {
		throw new RuntimeException(e);
			}
	}
	
	private static Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/Aula1?user=root&password=pedro&useTimezone=true&serverTimezone=UTC");
		}
	
	public static void criar(Pais pais) {
		String sqlInsert = "INSERT INTO Paises(id, nome, populacao, area) VALUES (?, ?, ?, ?)";
		try (Connection conn = obtemConexao();PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, pais.getId());
			stm.setString(2, pais.getnome());
			stm.setLong(3, pais.getPop());
			stm.setDouble(4, pais.getArea());
			stm.execute();
			conn.close();
			System.out.println(pais.getnome() + ", " + pais.getPop() + ", " + pais.getArea());
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		}
	
	public static void atualizar(Pais atualizado) {
		String sqlUpdate = "UPDATE Paises SET nome=?, populacao=?, area=? WHERE nome=?";
		try (Connection conn = obtemConexao();PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, atualizado.getnome());
			stm.setLong(2, atualizado.getPop());
			stm.setDouble(3, atualizado.getArea());
			stm.setString(4, "Chile");
			stm.execute();
			conn.close();
			System.out.println(atualizado);
			}
		catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	public static void excluir(String nome) {
		String sqlDelete = "DELETE FROM Paises WHERE nome = ?";
		try (Connection conn = obtemConexao();PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setString(1, nome);
			stm.execute();
			conn.close();
			System.out.println(nome + " excluido");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public static Pais carregar (String nome) {
		
		String sqlSelect = "SELECT * FROM Paises WHERE nome = ?";
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			stm.setString(1, "Chile");
			stm.execute();
			try(ResultSet rs = stm.executeQuery();){
				if(rs.next()) 
					return new Pais(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getDouble(4));
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			conn.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Não achou");
		return new Pais(0, sqlSelect, 0, 0);
	}
	
	public static Pais getMaiorP(Pais pais) {
		String sqlMaiorPopulacao = "SELECT * FROM Paises ORDER BY Populacao DESC LIMIT 1";
		try(Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMaiorPopulacao);){
						try(ResultSet rs = stm.executeQuery()){
								if(rs.next())
									return new Pais(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getDouble(4));
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			conn.close();
			} 
			catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Não achou");
		return pais;
	}
	
	public static Pais getMenorA(Pais pais) {
		String sqlMenorArea = "SELECT * FROM Paises ORDER BY Area ASC LIMIT 1";
		try(Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMenorArea);){
			try(ResultSet rs = stm.executeQuery();){
				if(rs.next())
					return new Pais(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getDouble(4));
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Não achou");
		return pais;
	}
	
	public static Pais[] vetor() {
		String sqlTresPaises = "SELECT * FROM Paises";
		Pais paises[] = new Pais[3];
		try(Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlTresPaises);){
			try(ResultSet rs = stm.executeQuery()){
				for(int i = 0; i < 3; i++) {
					if(rs.next()) {
						paises[i] = new Pais(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getDouble(4));
					}
					else
						System.out.println("Não achou");
				}
				return paises;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			conn.close();
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return paises;
	}

}
