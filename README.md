# Bitcoin Blockchain REST API

## API Documentation
__Swagger Path__ : https://bitcoin-blockchain-rest-api.herokuapp.com/api/swagger-ui.html

### Bitcoin Wallet Resource
| Endpoint | Params | Description |
| -------- | ------ | -----------|
| /getBalanceByAddress | address[] | Get balance of a Bitcoin address |
| /getBalanceByPrivateKey | privateKeys[] | Get balance of a Bitcoin address by it's private key |
| /getWalletFromPrivateKey | privateKey | Generate Bitcoin Wallet by it's private key |
| /randomWallet | total | Generate random bitcoin wallets (it also return their balance) |

<br>

### Bitcoin Resource
| Endpoint | Params | Description |
| -------- | ------ | -----------|
| /randomAddress | total | Generate random Bitcoin address |
| /randomPrivateKey | total | Generate random private key |

<br>

### Bitcoin Utils Resource
| Endpoint | Params | Description |
| -------- | ------ | -----------|
| /privateKeyToAddress | privateKey[] | Convert private keys to bitcoin address |
| /privateKeyToPublicKey | privateKey[] | Convert private keys to public keys |
| /privateKeyToWif | privateKey[] | Convert private keys to bitcoin wallet import format (WIF)|
| /wifToPrivateKey | wif[] | Convert wif to private keys |
| /publicKeyToAddress | publicKey[] | Convert public keys to bitcon address |
