package com.honeywell.ims.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.dao.SettingsDao;

/**
 * Created by h134602 on 9/13/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

	@Mock
	private SettingsDao settingsDao;

	@InjectMocks
	private ScheduleService service = new ScheduleService();

	@Test
	public void testCheckForWateringOneHourAhead() throws Exception {

		Settings settings= Settings.createDefault();
		Mockito.when(settingsDao.getUserSettings(Mockito.anyString())).thenReturn(settings);

		//service.checkForWatering();

	}
}