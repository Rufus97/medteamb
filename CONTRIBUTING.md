
## <a name="commit"></a> Git Commit Guidelines

**È richiesto che tutto il progetto sia scritto in inglese, dalle variabili, ai commenti fino ai commit**

Abbiamo regole molto precise su come devono essere formattati i messaggi dei nostri commit su git.
Questo porta a **messaggi più leggibili** che sono facili da seguire quando si esamina la  **cronologia del progetto**. 

**Se questo non dovesse essere rispettato le vostre pull request verranno rifiutate senza alcuna motivazione.**


### Formato del Messaggio di Commit

Ogni messaggio di commit è composto da un **intestazione** e un **corpo**. L'header ha un formato speciale 
che include un **tipo**, uno **argomento**, e un **oggetto**:

```html
<tipo>(<argomento>): <oggetto>
<BLANK LINE>
<corpo>
```

> Nessuna linea del messaggio di commit può superare i 100 caratteri!<br/>
  Questo permette al messaggio di essere più facile da leggere su GitHub così come in vari strumenti Git.

##### Tipo

Deve essere uno dei seguenti:

* **feat**: Una nuova funzionalità
* **fix**: Una correzione di bug
* **docs**: Modifiche solo alla documentazione
* **style**: Modifiche che non influenzano il significato del codice
  (spazi bianchi, formattazione, punti e virgola mancanti, ecc.)
* **refactor**: Una modifica del codice che non corregge un bug né aggiunge una funzionalità
* **test**: Aggiunta di test mancanti
* **build**: Modifiche alle dipendenze o strumenti di compilazione

##### Argomento

L'argomento potrebbe essere qualsiasi cosa che aiuti a specificare lo scope (o la funzionalità) che sta cambiando.
Esempi
- fix(Unaccessible dependencies): 
- feat(generic dataModel): 

##### Oggetto

L'oggetto contiene una descrizione del cambiamento:

* non mettere in maiuscolo la prima lettera
* senza punto (.) alla fine

##### Corpo

Come nell'oggetto, utilizza l'imperativo.
Il corpo dovrebbe includere la motivazione per il cambiamento e confrontarlo con il codice precedente.

##### Esempi di Messaggi di Commit

```text
fix(autocomplete): don't show the menu panel when readonly

- this could sometimes happen when no value was selected

```
```text
feat(chips): trigger ng-change on chip addition/removal

- add test of `ng-change` for `md-chips`
- add docs regarding `ng-change` for `md-chips` and `md-contact-chips`
- add demo for ng-change on `md-chips`
- add demo for ng-change on `md-contact-chips`
```

### Nomenclatura dei branch

Regole:
1. **Minuscole e Separate da Trattino**:
Mantieni le lettere in minuscolo per i nomi dei rami e usa i trattini per separare le parole.

 #### **Esempi**:
* Corretto: feature/med-ref or bugfix/wrong-name.
* Errato: Feature/MedRef

2. **Caratteri Alfanumerici**: Utilizza solo caratteri alfanumerici (a-z, 0–9) e trattini.
   Evita la punteggiatura, gli spazi, gli underscore o qualsiasi carattere non alfanumerico.
* Corretto: feature/med-ref o bugfix/wrong-name.
* Sbagliato: feature/med ref, feature/med_ref

3. **Niente Trattini Continui**: Evita trattini continui in quanto possono essere confusi e difficili da leggere.
* Corretto: feature/med-ref
* Sbagliato: feature/med--ref
  
4. **Niente Trattini Finali**: Non terminare il nome del ramo con un trattino.
* Corretto: feature/med-ref
* Sbagliato: feature/med-ref-

5. **Descrittivi**: I nomi dei rami dovrebbero essere descrittivi e concisi, riflettendo idealmente il lavoro svolto sul ramo.
Non utilizzare i nomi degli sviluppatori nel ramo, cosa che ho osservato nei principianti.
* Corretto: feature/med-ref
* Sbagliato: marco-dev (Marco è il nome dello sviluppatore)
