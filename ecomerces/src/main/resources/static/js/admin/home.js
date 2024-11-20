const ctx = document.getElementById('transactionChart').getContext('2d');
const transactionChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ['Sale', 'Distribute', 'Return'],
        datasets: [{
            data: [20, 20, 20],  // Replace with actual data values
            backgroundColor: [
                '#4285F4', // Blue for 'Sale'
                '#F4B400', // Yellow for 'Distribute'
                '#EA4335'  // Red for 'Return'
            ],
            hoverOffset: 4,
            borderWidth: 5
        }]
    },
    options: {
        cutout: '75%',  // Creates the donut effect
        plugins: {
            legend: {
                display: true,
                position: 'bottom',
                labels: {
                    boxWidth: 20,
                    padding: 15
                }
            },
            tooltip: {
                callbacks: {
                    label: function (tooltipItem) {
                        return tooltipItem.label + ': ' + tooltipItem.raw;
                    }
                }
            }
        }
    }
});