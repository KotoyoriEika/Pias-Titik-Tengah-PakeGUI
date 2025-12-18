public class MidPointCalc {

    // ===== FUNCTIONS =====
    public static function f1 = x -> Math.exp(5 * x);
    public static function f2 = x -> Math.sqrt(x * x + 1);
    public static function f3 = x -> Math.pow(5, x);
    public static function f4 = x -> Math.log(x) / x;

    // ===== EXACT INTEGRALS =====
    public static double exact1(double a, double b) {
        return (Math.exp(5 * b) - Math.exp(5 * a)) / 5;
    }

    public static double exact2(double a, double b) {
        double Fa = (a * Math.sqrt(a * a + 1) + Math.log(a + Math.sqrt(a * a + 1))) / 2;
        double Fb = (b * Math.sqrt(b * b + 1) + Math.log(b + Math.sqrt(b * b + 1))) / 2;
        return Fb - Fa;
    }

    public static double exact3(double a, double b) {
        return (Math.pow(5, b) - Math.pow(5, a)) / Math.log(5);
    }

    public static double exact4(double a, double b) {
        return 0.5 * (Math.pow(Math.log(b), 2) - Math.pow(Math.log(a), 2));
    }

    // ===== MIDPOINT METHOD =====
    public static String calculate(function f, double a, double b, double h, double exact) {

        StringBuilder output = new StringBuilder();

        int n = (int) Math.ceil((b - a) / h);
        double step = (b - a) / n;

        double sum = 0;

        output.append("Metode Titik Tengah (Midpoint)\n");
        output.append(String.format("a = %.4f, b = %.4f%n", a, b));
        output.append(String.format("h input = %.4f%n", h));
        output.append("n dihitung = ").append(n).append("\n\n");

        output.append("i\t x_mid\t\t f(x_mid)\n");

        for (int i = 0; i < n; i++) {
            double xMid = a + (i + 0.5) * step;
            double fx = f.apply(xMid);
            sum += fx;

            output.append(
                String.format(
                    "%d\t %.6f\t %.6f%n",
                    i + 1, xMid, fx
                )
            );
        }

        double approx = sum * step;
double error = Math.abs(exact - approx);

output.append("\n---------------------------------\n");
output.append("sum = ")
      .append(String.format("%.6f", sum))
      .append("\n");

output.append("Hasil Pendekatan (sum × h) = ")
      .append(String.format("%.6f", approx))
      .append("\n");

output.append("Nilai Eksak = ")
      .append(String.format("%.6f", exact))
      .append("\n");

output.append("Galat = ")
      .append(String.format("%.6f", error));

        return output.toString();
    }
}
