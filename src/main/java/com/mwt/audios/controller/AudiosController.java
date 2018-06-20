package com.mwt.audios.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mwt.audios.ds.AudiosRepository;
import com.mwt.audios.ds.entities.Audio;

/**
 * A RESTFul controller for accessing audio information.
 * 
 * @author v.manea
 */
@RestController
@RequestMapping(value = "/audios")
public class AudiosController {

	protected Logger logger = Logger.getLogger(AudiosController.class.getName());
	
	protected AudiosRepository audiosRepository;
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('AUDIO') or hasRole('ADMIN')")
	public List<Audio> findAll() {
		List<Audio> audios = audiosRepository.findAll();

		return audios;
	}

	/**
	 * Create an instance plugging in the repository of Audios.
	 * 
	 * @param audiosRepository
	 *            An audio repository implementation.
	 */
	@Autowired
	public AudiosController(AudiosRepository audiosRepository) {
		this.audiosRepository = audiosRepository;

		logger.info("AudiosRepository says system has " + audiosRepository.countAudios() + " audios");
	}
}
