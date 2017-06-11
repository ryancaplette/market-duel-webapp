package com.marketduel.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.marketduel.dao.PlayerDao;
import com.marketduel.model.Player;

@Repository
public class PlayerDaoImpl implements PlayerDao {
	
	private NamedParameterJdbcTemplate template;

	@Autowired
	public PlayerDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public Player getPlayerbyUsername(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", username);
        
		String sql = "SELECT * FROM player WHERE username=:name";
		
        List<Player> list = template.query(
                    sql,
                    params,
                    userMapper);
        
        Player result = null;
        if(list != null && !list.isEmpty()) {
        	result = list.get(0);
        }
        
		return result;
	}
	
	@Override
	public Boolean deletePlayerbyUsername(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", username);
        
		String sql = "DELETE FROM player WHERE username=:name";
		
		int result = template.update(sql, params);
        //A delete should modify 1 row
        return (result == 1);
	}

	@Override
	public Boolean registerPlayer(Player player) {
		String sql = "INSERT INTO player (Username, Email, UserPwd) values (:username, :email, :pw)";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", player.getUsername());
        params.put("email", player.getEmail());
        params.put("pw", player.getPassword());
		
        int result = template.update(sql, params);
        System.out.println("Created Player " + player.getUsername());
	
        //A new add should modify 1 row
        return (result == 1);
	}

	private RowMapper<Player> userMapper = (rs, rowNum) -> {
		Player p = new Player();
		
		p.setPlayerId(rs.getInt("PlayerID"));
		p.setEmail(rs.getString("Email"));
		p.setUsername(rs.getString("Username"));
		p.setPassword(rs.getString("UserPwd"));
		
		return p;
	};
}