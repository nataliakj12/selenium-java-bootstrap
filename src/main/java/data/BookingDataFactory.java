package data;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.github.javafaker.Faker;
import config.Configuration;
import enums.RoomType;
import java.util.Locale;
import java.util.Random;
import lombok.extern.log4j.Log4j2;
import model.Booking;

@Log4j2
public class BookingDataFactory {

    private final Faker faker;

    public BookingDataFactory() {
        faker = new Faker(new Locale(new Configuration().getFakerLocale()));
    }

    public Booking createBookingData() {
        Booking booking = Booking.builder().
            email(faker.internet().emailAddress()).
            country(returnRandomCountry()).
            password(faker.internet().password()).
            dailyBudget(returnDailyBudget()).
            newsletter(faker.bool().bool()).
            roomType(RoomType.getRandom()).
            roomDescription(faker.lorem().paragraph()).
            build();

        log.info(booking);
        ExtentTestManager.getTest().info(booking.toString());
        return booking;
    }

    private String returnRandomCountry() {
        return new String[] {"Belgium", "Brazil", "Netherlands"}[(new Random().nextInt(3))];
    }

    private String returnDailyBudget() {
        return new String[] {"$100", "$100 - $499", "$499 - $999", "$999+"}[(new Random().nextInt(4))];
    }
}
