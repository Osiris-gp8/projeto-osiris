import { Container } from "./style";
import Chart from "react-google-charts";
export default () => {
    return (
        <Container>
        <Chart
            width={'100%'}
            height={'400px'}
            chartType="ScatterChart"
            loader={<div>Loading Chart</div>}
            data={[
                ['Tempo no site', 'Valor da venda'],
                [8, 750],
                [4, 100],
                [11, 50],
                [4, 10],
                [3, 200],
                [6.5, 7],
            ]}
            options={{
                title: 'Cluste de cliente',
                hAxis: { title: 'Tempo'},
                vAxis: { title: 'Valor', minValue: 0, maxValue: 1000 },
                legend: 'none',
                
            }}
            rootProps={{ 'data-testid': '1' }}
        />

        </Container >
    );
}