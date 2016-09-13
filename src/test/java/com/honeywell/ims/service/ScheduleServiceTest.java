package com.honeywell.ims.service;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.honeywell.ims.dao.SettingsDao;
import com.honeywell.ims.domain.UserSettings;

import static org.junit.Assert.*;

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
		Calendar tomorow = Calendar.getInstance();
		//tomorow.add(Calendar.HOUR, 1);
		UserSettings userSettings = new UserSettings(null, tomorow.getTime(), 0);

		Mockito.when(settingsDao.getUserSettings(Mockito.anyString())).thenReturn(userSettings);

		//service.checkForWatering();

	}
}