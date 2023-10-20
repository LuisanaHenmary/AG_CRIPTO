import java.util.ArrayList;

public class Wallets {

    private static double[][] assets; // precios de los activos [fecha][activo]
    private static ArrayList<ArrayList<Double>> assets_return; // Rendimiento del i -ésimo activo entre el período t y el período t-1
    private static ArrayList<Double> portfolio_return; // Rendimiento de la cartera en el momento t

    public Wallets(double[][] all_price) {
        // Constructor
        assets_return = new ArrayList<>();
        portfolio_return = new ArrayList<>();
        assets = all_price;
    }

    private static void CalculateAssetReturn(){
        //Calcular el rendimiento del i -ésimo activo entre el período t y el período t-1

        for (double[] asseti : assets) {
            ArrayList<Double> asset_return_row = new ArrayList<>();

            for (int t = 1; t < asseti.length; t++) {
                double performance = (asseti[t] - asseti[t - 1]) / asseti[t - 1];

                asset_return_row.add(performance);

            }
            assets_return.add(asset_return_row);
        }
    }

    private static void CalculatePortfolioReturn(double [] x){
        //Calcular el rendimiento de la cartera en el momento t

        CalculateAssetReturn();
        double aux;
        int max_t = assets_return.get(0).size();

        for (int t = 0; t < max_t; t++) {
            aux = 0;

            for (int i = 0; i < x.length; i++) {
                aux += assets_return.get(i).get(t)*x[i];
            }

            portfolio_return.add(aux);
        }
    }



    private static double Sharpe(double [] x){
        //Indice de Sharpe
        double temp = 0;
        double average, variance;

        CalculatePortfolioReturn(x);
        average = portfolio_return.stream().mapToDouble(a -> a).average().orElse(0.0); //Media

        for (double val : portfolio_return) {
            double squrDiffToMean = Math.pow(val - average, 2);
            temp += squrDiffToMean;
        }

        variance = Math.sqrt(temp / (double) (portfolio_return.size())); //Varianza

        return (average/variance);
    }

    private static double Constraint(double [] x){
        //Restricciones

        double constr_limit, sum = 0;

        for (double xi : x)
            sum += xi;

        constr_limit = Math.pow(sum - 1, 2); //Restriccion suma x = 1

        for (double xi : x) {
            constr_limit +=
                    Math.pow(Math.max(0.0, xi - 1), 2) + //  Restricción "x <= 1"
                    Math.pow(Math.max(0.0, -xi), 2); //  Restricción "x >= 0"
        }

        return constr_limit;
    }

    // La funcion objetivo
    public double objetive(double [] x){
        return (-Sharpe(x) + 100*Constraint(x));
    }

}
