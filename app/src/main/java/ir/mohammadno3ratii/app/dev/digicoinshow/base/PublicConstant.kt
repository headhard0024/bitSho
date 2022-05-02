package ir.mohammadno3ratii.app.dev.digicoinshow.base

enum class PublicConstant(val value: String) {

    TAG("MONO"),
    BASE_URL("https://api.coinlore.net/api/"),
    APP_THEME("appTheme"),
    COIN_EXCHANGE_BASE("coinExchangeBaseKey"),
    COIN_UPDATE_SPEED("coinUpdateSpeed"),
    TOMAN("تومان"),
    DOLLAR("دلار"),

    internetIsNotAvailable("اینترنت در دسترس نیست!"),
}

enum class PublicConstantNumber(val value: Int) {
    APP_THEME_LIGHT(1),
    APP_THEME_DARK(2),
    APP_THEME_DEVISE_DEFAULT(3),

    TOMAN_ID(1),
    DOLAR_ID(2),

    COIN_UPDATE_SPEED_THIRTY_SECONDS(1),
    COIN_UPDATE_SPEED_ONE_MINUTRS(2),
    COIN_UPDATE_SPEED_FIVE_MINUTRS(3),
    COIN_UPDATE_SPEED_TEN_MINUTRS(4),
}