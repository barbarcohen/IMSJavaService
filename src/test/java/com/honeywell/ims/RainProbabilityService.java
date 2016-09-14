package com.honeywell.ims;

import com.honeywell.ims.api.weather.Data;
import com.honeywell.ims.api.weather.Forecast;
import com.honeywell.ims.api.weather.Rain;
import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.dao.ForecastDao;
import com.honeywell.ims.domain.RainProbability;
import com.honeywell.ims.service.ForecastService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by E596692 on 9/14/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class RainProbabilityService {

    @Mock
    private ForecastDao forecaSettingsDao;

    @InjectMocks
    private ForecastService forecastService;

    @Before
    public void setup() {
        //forcaSettingsDao = mock(ForecastDao.class);
        //forecastService = new ForecastService();
    }

    @Test
    public void getRainProbaliltyWhenRains() throws Exception {
        Settings s = Settings.createDefault();
        s.setNextWateringDate(DateTime.now().toDate());
        s.setDelay(7);
        Forecast fc = new Forecast();
        List<Data> data = new LinkedList<>();
        data.add(GetData(3, 5));
        data.add(GetData(6, 10));
        data.add(GetData(9, 20));
        fc.setDataList(data);

        Mockito.when(forecaSettingsDao.getLatestForecast()).thenReturn(fc);

        RainProbability rainProbability = forecastService.getRainProbability(s);
        assert (rainProbability.getRainProbability() == 100);
        assert (rainProbability.getDownfall() == 15);
    }

    @Test
    public void getRainProbaliltyWithoutRains() throws Exception {
        Settings s = Settings.createDefault();
        s.setNextWateringDate(DateTime.now().toDate());
        s.setDelay(7);
        Forecast fc = new Forecast();
        List<Data> data = new LinkedList<>();
        data.add(GetData(3, 0));
        data.add(GetData(6, 0));
        data.add(GetData(9, 0));
        fc.setDataList(data);

        Mockito.when(forecaSettingsDao.getLatestForecast()).thenReturn(fc);

        RainProbability rainProbability = forecastService.getRainProbability(s);
        assert (rainProbability.getRainProbability() == 0);
        assert (rainProbability.getDownfall() == 0);
    }

    public Data GetData(int hours, int downfall) {
        Data d = new Data();
        if (downfall > 0) {
            Rain r = new Rain();
            r.setDownfall(downfall);
            d.setRain(r);
        }

        d.setDt(DateTime.now().plusHours(hours).toDate());

        return d;
    }
}