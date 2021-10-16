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

function formatDate(date = new Date()){
    if(date.getDate() < 10)
        return `${date.getFullYear()}-${date.getMonth() + 1}-0${date.getDate()}`
    return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`
}

