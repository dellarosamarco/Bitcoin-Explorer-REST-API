# Bitcoin Blockchain REST API

## API Documentation
__Api Path__ : https://bitcoin-blockchain-rest-api.herokuapp.com/api</br>
__Swagger Path__ : https://bitcoin-blockchain-rest-api.herokuapp.com/api/swagger-ui.html

### Bitcoin Resource
| Endpoint | Params | Description |
| -------- | ------ | -----------|
| /randomAddress | total | Generate random bitcoin address |
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

<br>

### Bitcoin Wallet Resource
| Endpoint | Params | Description |
| -------- | ------ | -----------|
| /getBalance | address[] | Get balance of a bitcoin address |
| /randomWallet | total | Generate random bitcoin wallets (it also return their balance) |
