package site.metacoding.red.domain.loves;

import java.util.List;

public interface LovesDao {
	public void insert(Loves loves);
	public Loves findByUsername(String username);
	public List<Loves> findAll();
	public Loves findById(Integer id);
	public void update(Loves loves);
	public void deleteById(Integer id);
	
}
