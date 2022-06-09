import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedTests {


  @ParameterizedTest(name = "{index} test if {0} is even number")
  @ValueSource(ints = {6, 2, 8, 10, 16, 100})
  void isEven_ShouldReturnTrueForEvenNumbers(int number) {
    assertTrue(isEvenNumber(number));
  }

  public static boolean isEvenNumber(int number) {
    if (number % 2 == 0) return true;
    else return false;
  }


  @ParameterizedTest(name = "{index} test if word: {0}-- is a palindrome")
  @ValueSource(strings = {"level", "noon", "refer"})
  void testPalindrome(String word) {
    assertTrue(isPalindrome(word));
  }

  public boolean isPalindrome(String s) {
    return s == null ? false : StringUtils.reverse(s).equals(s);
  }

  @ParameterizedTest(name = "{index} test if the employee named {0} has age {1} which >=30")
  @CsvSource(value = {
          "Ivan, 30",
          "Olga, 35",
          "Maria, 40"})
  void testWithCsvSource(String name, int age) {
    assertNotNull(name);
    assertTrue(age >= 30);
  }

  @ParameterizedTest(name = "{index} test if {0} has 31 days")
  @EnumSource(
          value = Month.class,
          names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER", "FEBRUARY"},
          mode = EnumSource.Mode.EXCLUDE)
  void checkLengthOfMonth(Month month) {
    final boolean isALeapYear = false;
    assertEquals(31, month.length(isALeapYear));
  }

  @ParameterizedTest(name = "{index} test if adding of numbers {0} and {1} is {2}")
  @MethodSource("addNumbers")
  void add(int a, int b, int result) {
    Assertions.assertEquals(result, a + b);
  }

  private static Stream<Arguments> addNumbers() {
    return Stream.of(
            Arguments.of(4, 4, 8),
            Arguments.of(10, 1, 11),
            Arguments.of(-2, -2, -4));
  }

}



