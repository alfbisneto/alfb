package Pais;

public class Service {
	
	public static void criar(TO to) {
		DAO.criar(to);
	}
	
	public static TO carregar(int id) {
		return DAO.carregar(id);
	}
	
	public static void atualizar(int id, TO to) {
		DAO.atualizar(id, to);
	}
	
	public static void excluir(int id) {
		DAO.excluir(id);
	}
	
	public static TO getMaiorP() {
		return DAO.getMaiorP();
	}
	
	public static TO getMenorA() {
		return DAO.getMenorA();
	}
	
	public static Pais[] vetor() {
		return DAO.vetor();

}	
	
}
