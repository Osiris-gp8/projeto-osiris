import { Container } from "./style";
import Chart from "react-google-charts";
export default () => {
    return (
        <Container>
            <Chart
                width={'100%'}
                height={'400px'}
                chartType="LineChart"
                loader={<div>Carregando gráfico</div>}
                data={[
                    ['Acessos', 'Vendas'],
                    [0, 380],
                    [1, 200],
                    [2, 100],
                    [3, 50],
                    [4, 70],
                    [5, 99],
                    [6, 88],
                    [7, 20],
                    [8, 40],
                    [9, 40],
                    [10, 32],
                    [11, 35],
                ]}
                options={{
                    hAxis: {
                        title: 'Acessos',
                    },
                    vAxis: {
                        title: 'Vendas',
                        minValue: 0, maxValue: 1000
                    },
                    colors:['#4329B5'],
                    title:'Relação acessos X Vendas'
                }}
                rootProps={{ 'data-testid': '1' }}
            />
        </Container>
    );
}

{/* <Chart
                width={'100%'}
                height={'400px'}
                chartType="LineChart"
                loader={<div>Carregando Gráfico</div>}
                data={[
                  ['Horas de acesso', 'Valor de compra'],
                  [0, 67],
                  [1, 88],
                  [2, 77],
                  [3, 93]
                ]}
                options={{
                  // Material design options
                  chart: {
                    title: "Cluster de Cliente",
                  },
                  hAxis: { title: 'Horas de acesso' },
                  vAxis: { title: 'Grade' },
                  colors: ['#36B8FF'],
                  titleTextStyle: {
                    color: 'black',
                    fontSize: 12}
                }}
                rootProps={{ 'data-testid': '3' }}
            /> */}