package uk.ac.tees.c2704207.currency.data.local.entity

import uk.ac.tees.c2704207.currency.domain.model.CurrencyRate

fun currencyRateEntity.toCurrencyRate(): CurrencyRate{
    return CurrencyRate(

        code = code,
        name = name,
        rate = rate

    )


}
fun CurrencyRate.toCurrencyRateEntity(): currencyRateEntity {
    return currencyRateEntity(

        code = code,
        name = name,
        rate = rate

    )
}