package com.mwt.audios.ds;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.mwt.audios.ds.entities.Audio;

/**
 * Repository for Audio data implemented using Spring Data JPA.
 * 
 * @author v.manea
 */
public interface AudiosRepository extends Repository<Audio, Long> {
	
	public List<Audio> findAll();

	@Query("SELECT count(*) from Audio")
	public int countAudios();
}
