export function getIntervalMonthDays(){
    const date = new Date(), y = date.getFullYear(), m = date.getMonth();
    const firstDay = formatDate( new Date(y, m, 1));
    // const lastDay = formatDate(new Date(y, m + 1, 0));
    const lastDay = formatDate(new Date('2021/10/10'))
    return [firstDay, lastDay]
}

export function getIntervalWeekDays(){
    const date = new Date();
    const lastDay = formatDate(date);
    date.setDate(date.getDate() - 6)
    const firstDay = formatDate(date);
    return [firstDay, lastDay]
}

export function getIntervalSixMonths(){
    const seisMeses = 6 * 30 * 24 * 60 * 60 * 1000
    const dataInicial = new Date(Math.abs( new Date() -  seisMeses))
    const dataFinal = new Date()
    return { 
        dataInicio: formatDate(dataInicial), 
        dataFinal: formatDate(dataFinal) 
    }
}

function formatDate(date = new Date()){
    return date.toISOString().split('T')[0];
}

export function formatDateTime(date = new Date()){
    let year
    let month = date.getMonth() + 1;
    let day
    let hours = date.getHours()
    let mins = date.getMinutes()
    let sec = date.getSeconds()
    day = date.getDate() < 10 ? `0${date.getDate()}` : date.getDate()
    month = month< 10 ? `0${month}` : month
    year = date.getFullYear()
    return `${year}-${month}-${day}T${hours}:${mins}:${sec}`
}

