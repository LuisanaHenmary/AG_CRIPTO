
public class Main {

    public static void main(String[] args) {

        int rows = 6, n_winners = 3, generation = 1, max_generation = 30;

        double[][] asset_r = {

                {3790.57, 3648.11, 3505.65, 3363.19, 3220.73, 3078.27, 2935.81, 2793.35, 2650.89, 2508.43, 2365.97, 2223.51,
                        12971.86, 12673.66, 12375.46, 12077.26, 11779.06, 11480.86, 11182.66, 10884.46, 10586.26, 10288.06, 9989.86, 9691.66,
                        29824.42, 29162.10, 28771.12, 28384.34, 28057.94, 27777.35, 27499.62, 27226.12, 26956.87, 26692.82, 26434.11, 26181.72,
                        40854.74, 40573.30, 39646.22, 39102.69, 38772.53, 37670.90, 37046.08, 36654.20, 36780.61, 36891.77, 37030.71, 37152.90,
                        28797.73, 26627.72, 25883.51, 26689.95, 27275.60, 26045.00, 26215.31, 26605.88, 26904.94, 27250.20},

                {232.49, 218.47, 204.45, 190.43, 176.41, 162.39, 148.37, 134.35, 120.33, 106.31, 92.29, 78.27,
                        4120.79, 3884.77, 3648.75, 3412.73, 3176.71, 2940.69, 2704.67, 2468.65, 2232.63, 1996.61, 1760.59, 1524.57,
                        1734.51, 1598.49, 1462.47, 1326.45, 1190.43, 1054.41, 918.39, 782.37, 646.35, 510.33, 374.31, 238.29,
                        2721.21, 2585.20, 2449.19, 2313.18, 2177.17, 2041.16, 1905.15, 1769.14, 1633.13, 1497.12, 1361.11, 1225.10,
                        1635.59, 1633.58, 1645.62, 1611.58, 1607.05, 1602.52, 1597.99, 1593.46, 1588.93, 1584.40},

                {0.78, 0.77, 0.76, 0.75, 0.74, 0.73, 0.72, 0.71, 0.7, 0.69, 0.68, 0.67,
                        0.97, 0.96, 0.95, 0.94, 0.93, 0.92, 0.91, 0.9, 0.89, 0.88, 0.87, 0.86,
                        1.15, 1.14, 1.13, 1.12, 1.11, 1.1, 1.09, 1.08, 1.07, 1.06, 1.05, 1.04,
                        0.95, 0.94, 0.93, 0.92, 0.91, 0.9, 0.89, 0.88, 0.87, 0.86, 0.85, 0.84,
                        0.76, 0.75, 0.74, 0.73, 0.72, 0.71, 0.7, 0.69, 0.68, 0.67},

                {24.83, 41.95, 59.07, 76.19, 93.31, 110.43, 127.55, 144.67, 161.79, 178.91, 196.03, 213.15,
                        50.65, 67.77, 84.89, 102.01, 119.13, 136.25, 153.37, 170.49, 187.61, 204.73, 221.85, 238.97,
                        76.47, 93.59, 110.71, 127.83, 144.95, 162.07, 179.19, 196.31, 213.43, 230.55, 247.67, 264.79,
                        102.29, 119.41, 136.53, 153.65, 170.77, 187.89, 205.01, 222.13, 239.25, 256.37, 273.49, 290.61,
                        128.11, 145.23, 162.35, 179.47, 196.59, 213.71, 230.83, 247.95, 265.07, 282.19},

                {2.65, 2.64, 2.63, 2.62, 2.61, 2.6, 2.59, 2.58, 2.57, 2.56, 255, 254,
                        2.12, 2.11, 2.1, 2.09, 2.08, 2.07, 2.06, 2.05, 2.04, 2.03, 202, 201,
                        1.59, 1.58, 1.57, 1.56, 1.55, 1.54, 1.53, 1.52, 1.51, 1.5, 1.49, 1.48,
                        1.06, 1.05, 1.04, 1.03, 1.02, 1.01, 1, 0.99, 0.98, 0.97, 0.96, 0.95,
                        0.53, 0.52, 0.51, 0.5, 0.49, 0.48, 0.47, 0.46, 0.45, 0.44},

                {50.2, 48.19, 46.18, 44.17, 42.16, 40.15, 38.14, 36.13, 34.12, 32.11, 30.1, 28.09,
                        132.6, 128.7, 125.8, 122.9, 120, 117.1, 114.2, 111.3, 108.4, 105.5, 102.6, 99.7,
                        207.16, 195.05, 182.94, 170.83, 158.72, 146.61, 134.5, 122.39, 110.28, 98.17, 86.06, 73.95,
                        392.58, 381.47, 370.36, 359.25, 348.14, 337.03, 325.92, 314.81, 303.7, 292.59, 281.48, 270.37,
                        68.76, 67.65, 66.54, 65.43, 64.32, 63.21, 62.1, 61.09, 59.98, 58.87},

                {1321.4, 1273.3, 1225.2, 1177.1, 1129, 1080.9, 1032.8, 984.7, 936.6, 888.5, 840.4, 792.3,
                        1090, 1051.9, 1013.8, 975.7, 937.6, 899.5, 861.4, 823.3, 785.2, 747.1, 709, 670.9,
                        856.1, 828, 799.9, 771.8, 743.7, 715.6, 687.5, 659.4, 631.3, 603.2, 575.1, 547,
                        612.3, 594.2, 576.1, 558, 539.9, 521.8, 503.7, 485.6, 467.5, 449.4, 431.3, 413.2,
                        378.5, 370.4, 362.3, 354.2, 346.1, 338, 329.9, 321.8, 313.7, 305.6},

                {0.6, 0.59, 0.58, 0.57, 0.56, 0.55, 0.54, 0.53, 0.52, 0.51, 0.5, 0.49,
                        0.5, 0.49, 0.48, 0.47, 0.46, 0.45, 0.44, 0.43, 0.42, 0.41, 0.4, 0.39,
                        0.4, 0.39, 0.38, 0.37, 0.36, 0.35, 0.34, 0.33, 0.32, 0.31, 0.3, 0.29,
                        0.33, 0.32, 0.31, 0.3, 0.29, 0.28, 0.27, 0.26, 0.25, 0.24, 0.23, 0.22,
                        0.26, 0.25, 0.24, 0.23, 0.22, 0.21, 0.2, 0.19, 0.18, 0.17},

                {0.42, 0.41, 0.4, 0.39, 0.38, 0.37, 0.36, 0.35, 0.34, 0.33, 0.32, 0.31,
                        0.35, 0.34, 0.33, 0.32, 0.31, 0.3, 0.29, 0.28, 0.27, 0.26, 0.25, 0.24,
                        0.28, 0.27, 0.26, 0.25, 0.24, 0.23, 0.22, 0.21, 0.2, 0.19, 0.18, 0.17,
                        0.23, 0.22, 0.21, 0.2, 0.19, 0.18, 0.17, 0.16, 0.15, 0.14, 0.13, 0.12,
                        0.18, 0.17, 0.16, 0.15, 0.14, 0.13, 0.12, 0.11, 0.1, 0.09},


        };


        Wallets wallets = new Wallets(asset_r);
        GeneticAlgorithm ga = new GeneticAlgorithm(rows, n_winners, asset_r.length);
        ga.initPopulation();

        int randomNumber1 = (int) (Math.random() * 100);
        int randomNumber2 = (int) (Math.random() * 100);

        while (generation <= max_generation){
            System.out.println("\n\n\n GENERACION " + generation);


            ga.qualityOfTheIndividual(wallets);
            ga.adaptability();
            ga.viewPopulation();

            // Competencia para determinar que individuos sobreviven
            ga.qualityOfTheIndividual(wallets);
            ga.adaptability();
            ga.couplesSelection();
            ga.tournament();
            ga.viewWinners();

            //Nueva poblacion con los sobrevivientes
            ga.copied();
            ga.qualityOfTheIndividual(wallets);
            ga.adaptability();
            ga.viewPopulation();

            // Seccion de parejas en caso de combinacion
            ga.couplesSelection();

            if (50 <= randomNumber1 && randomNumber1 <= 90){
                System.out.println("\nProbabilidad de cruce: " + randomNumber1);
                ga.combination();
                ga.qualityOfTheIndividual(wallets);
                ga.adaptability();
                ga.viewPopulation();
            }else {
                System.out.println("\nLa probabilidad de cruza: (" +
                        randomNumber1 +") no esta entre 50 y 90. No hay cruza\n");
            }

            if (0 <= randomNumber2 && randomNumber2 <= 75){
                System.out.println("\nProbabilidad de mutacion: " + randomNumber1);
                ga.mutation();
                ga.qualityOfTheIndividual(wallets);
                ga.adaptability();
                ga.viewPopulation();
            }else {
                System.out.println("\nLa probabilidad de mutacion: (" +
                        randomNumber2 +") no esta entre 0 y 75. No hay mutacion\n");
            }

            generation++;
        }

        ga.viewPopulation();
        ga.SearchBest();
    }
}