package pack.config

class AccountException(accountResCode: AccountResCode): RuntimeException() {
    public val accountResCode: AccountResCode = accountResCode
}