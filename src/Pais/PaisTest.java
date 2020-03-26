package Pais;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {

	Pais original, copia, menor;
	static int id = 0;
	
	@Before
	public void setUp() {
		System.out.println("Setup");
		original = new Pais(id, "Chile",18, 756950.0);
		copia = new Pais(id, "Chile",18, 756950.0);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("Carregar");
		Pais fixture = new Pais(1,"Brasil", 209, 8511000.0);
		Pais novo = new Pais(0, null, 0, 0);
		novo = CRUD.carregar("Brasil");
		assertEquals(fixture, novo);
	}
	
	@Test
	public void test01Criar() {
	System.out.println("criar");
	CRUD.criar(original);
	id = original.getId();
	System.out.println(id);
	copia.setId(id);
	assertEquals(original, copia);
	}
	
	@Test
	public void test02Atualizar() {
	System.out.println("Atualizar");
	original.setPop((10));
	copia.setPop(10);
	CRUD.atualizar(original);
	assertEquals(original, copia);
	}
	
	@Test
	public void test03Excluir() {
	System.out.println("Excluir");
	CRUD.excluir("Chile");
	original = CRUD.carregar("Chile");
	Pais nada = new Pais(0, null, 0, 0);
	assertEquals(original, nada);
	}
	
	@Test
	public void test04Maior() {
	System.out.println("Maior pop");
	original.setPop(10);
	copia.setPop(10);
	CRUD.criar(original);
	copia = CRUD.getMaiorP(copia);
	assertEquals(original, copia);
	}
	
	@Test
	public void test05Menor() {
		System.out.println("Menor area");
		original.setArea(100000.0);
		copia.setArea(100000.0);
		CRUD.criar(original);
		copia = CRUD.getMenorA(copia);
		assertEquals(original, copia);
	}
	
	@Test
	public void test06() {
		Pais v[] = CRUD.vetor();
		Pais pais[] = {new Pais(1, "Brasil", 209, 8511000.0), new Pais(2, "EUA", 307, 9834000.0), new Pais(3, "Argentina", 44, 2780000.0)};
		assertArrayEquals(pais, v);
	}

}
