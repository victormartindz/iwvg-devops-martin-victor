package es.upm.miw.iwvg_devops.code;

import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Searches {
    public Stream<String> findUserFamilyNameByUserNameDistinct(String userName) {
        return new UsersDatabase().findAll()
                .filter(user -> userName.equals(user.getName()))
                .map(User::getFamilyName)
                .distinct();
    }

    public Stream<Integer> findFractionNumeratorByUserFamilyName(String userFamilyName) {
        return new UsersDatabase().findAll()
                .filter(user -> userFamilyName.equals(user.getFamilyName()))
                .flatMap(user -> user.getFractions().stream()
                        .filter(Objects::nonNull)
                )
                .map(Fraction::getNumerator);
    }

    public Stream<String> findUserFamilyNameByFractionDenominator(int fractionDenominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .anyMatch(fraction -> fractionDenominator == fraction.getDenominator()))
                .map(User::getFamilyName);
    }

   public Fraction findFractionAdditionByUserId(String id) {

       User user = new UsersDatabase().findAll()
               .filter(anUser -> id.equals(anUser.getId()))
               .findFirst()
               .orElseThrow(() -> new RuntimeException("User not found"));

       List<Fraction> fractions = user.getFractions();
       Fraction sum = new Fraction(0, 1);

       for (Fraction fraction : fractions) {
           sum = sum.add(fraction);
       }

       return sum;
   }

    public Stream<String> findUserIdByAllProperFraction() {
        return Stream.empty();
    }

    public Fraction findHighestFraction() {
        return null;
    }

    public Stream<Double> findDecimalFractionByNegativeSignFraction() {
        return Stream.empty();
    }

}