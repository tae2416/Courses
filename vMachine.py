uInput = 'run'
quant = dict()
price = dict()
hist = list()
balance = {
    'dollar': 5,
    'quarter': 10, 
    'dime': 10, 
    'nickel': 10,
    'penny': 10 
}

while uInput[0] != 'exit':
    # Read in user input and keep track of input history
    uInput = input('What do you want to do?: ')
    hist.append(uInput)
    uInput = uInput.split(' ')
    print('')

    # Determine index of quantity in user input
    i = 0;
    qInd = 0;
    for x in uInput:
        if x.isdigit():
            qInd = i
        i += 1

    match uInput[0]:
        case 'exit':
            # Exit program
            print('Completing transaction...')
        case 'inventory':
            # Print inventory
            print('{: ^25s} | {: ^25s} | {: ^25s}'.format('Item', 'Quantity', 'Price'))
            print('{:-^85s}'.format('-'))
            for x in quant:
                print('{: ^25s} | {: ^25d} | {: ^25s}'.format(x, quant[x], '$ '+str(price[x])))
        case 'balance':
            # Print balance
            print('{: ^20s} | {: ^15s} | {: ^15s} | {: ^15s} | {: ^15s}'\
                .format('Dollar', 'Quarter', 'Dime', 'Nickel', 'Penny'))
            print('{:-^95s}'.format('-'))
            print('{: ^20} | {: ^15} | {: ^15} | {: ^15} | {: ^15}'\
                .format(balance['dollar'], balance['quarter'], balance['dime'], \
                balance['nickel'], balance['penny']))
        case 'history':
            # Print history
            print('Input Log')
            print('{:-^40s}'.format('-'))
            for x in hist:
                print(x)
        case 'help':
            # Print help msg
            print('{: <30s} | {: <25s} | {: <25s}'.format('Command Syntax', 'Example', 'Description'))
            print('{:-^115s}'.format('-'))
            print('{: <30s} | {: <25s} | {: <25s}'.format('balance', 'balance', 'Shows the balance'))
            print('{:-^115s}'.format('-'))
            print('{: <30s} | {: <25s} | {: <25s}'.format('history', 'history', 'Prints list of transactions'))
            print('{:-^115s}'.format('-'))
            print('{: <30s} | {: <25s} | {: <25s}'.format('inventory', 'inventory', 'Prints available items with name and ID'))
            print('{:-^115s}'.format('-'))
            print('{: <30s} | {: <25s} | {: <25s}'.format('add item <str> <int> <float>', \
                'add item chips 2 $1.00', 'Add an item name qty price'))
            print('{:-^115s}'.format('-'))
            print('{: <30s} | {: <25s} | {: <25s}'.format('buy item \{5\}', 'buy item chips 1 2 2 4 3', \
                'Buys an item with # dollars, quarters, dimes, nickles, ')) 
            print('{: <30s} | {: <25s} | {: <25s}'.format('', '', \
                'pennies. It also shows change given and the remaining '))
            print('{: <30s} | {: <25s} | {: <25s}'.format('', '', \
                'balance with currency distribution.  For change, the '))
            print('{: <30s} | {: <25s} | {: <25s}'.format('', '', \
                'machine  uses the largest denominator of curenncy that '))
            print('{: <30s} | {: <25s} | {: <25s}'.format('', '', \
                'is available.'))
            print('{:-^115s}'.format('-'))
            print('{: <30s} | {: <25s} | {: <25s}'.format('exit', 'exit', 'Exit the vending machine'))
            print('{:-^115s}'.format('-'))
        case ('add' | 'buy'):
            match uInput[1]:
                case 'item':
                    # Get item name        
                    j = 2
                    itemName = uInput[j]   
                    while j < qInd:
                        j += 1
                        itemName = itemName + ' ' + uInput[j]
                    
                    match uInput[0]:
                        case 'add':      
                            # Add item to inventory
                            quant[itemName] = int(uInput[qInd])
                            price[itemName] = float(uInput[qInd+1][1:len(uInput[qInd+1])])
                        case 'buy':    
                            # Sell inventory items
                            print('Working on it')    
                case other:
                    # Print error msg
                    print('Invalid command. Type \'help\' for more info.')
        case other:
            # Print error msg
            print('Invalid command. Type \'help\' for more info.')

    print(quant)
    print(price)
    print(hist)
    print(len(uInput))
    for k in range(3):
        print('')
    

