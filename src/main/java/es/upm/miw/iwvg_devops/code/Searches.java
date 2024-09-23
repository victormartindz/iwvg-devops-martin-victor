package es.upm.miw.iwvg_devops.code;

import java.util.Comparator;
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
        return new UsersDatabase().findAll()
                .filter(anUser -> anUser.getFractions()
                        .stream()
                        .allMatch(Fraction::isProper))
                .map(User::getId);
    }

    public Fraction findHighestFraction() {
        Comparator<Fraction> fractionComparator = Comparator.comparing(Fraction::decimal);

        return new UsersDatabase().findAll()
                .flatMap(user -> user.getFractions().stream())
                .filter(fraction -> fraction.getDenominator() != 0)
                .max(fractionComparator)
                .orElseThrow(() -> new RuntimeException("No fraction found."));
    }

    public Stream<Double> findDecimalFractionByNegativeSignFraction() {
        return new UsersDatabase().findAll()
                .flatMap(anUser -> anUser.getFractions().stream())
                .filter(aFraction -> (
                        aFraction.getNumerator() < 0 || (aFraction.getNumerator() > 0 && aFraction.getDenominator() < 0)))
                .map(Fraction::decimal);
    }

}