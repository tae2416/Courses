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

# Check string for decimal number
def isFloat(string):
    try:
        float(string)
        return True
    except ValueError:
        return False
    
# Verify whether a list of strings contains numbers
def hasNum(list):
    for x in list:
        if x.isdigit():
            return True
    return False

while uInput[0] != 'exit' or len(uInput) != 1:
    # Read in user input and keep track of input history
    uInput = input('What do you want to do?: ')
    hist.append(uInput)
    uInput = uInput.split(' ')
    print('')
    print(hasNum(uInput))

    match uInput[0]:
        case 'exit' if len(uInput) ==1:
            # Exit program
            print('Completing transaction...')
        case 'inventory' if len(uInput) ==1:
            # Print inventory
            print('{: ^25s} | {: ^25s} | {: ^25s}'.format('Item', 'Quantity', 'Price'))
            print('{:-^85s}'.format('-'))
            for x in quant:
                print('{: ^25s} | {: ^25d} | {: ^25s}'.format(x, quant[x], '$ '+str(price[x])))
        case 'balance' if len(uInput) ==1:
            # Print balance
            print('{: ^20s} | {: ^15s} | {: ^15s} | {: ^15s} | {: ^15s}'\
                .format('Dollar', 'Quarter', 'Dime', 'Nickel', 'Penny'))
            print('{:-^95s}'.format('-'))
            print('{: ^20} | {: ^15} | {: ^15} | {: ^15} | {: ^15}'\
                .format(balance['dollar'], balance['quarter'], balance['dime'], \
                balance['nickel'], balance['penny']))
        case 'history' if len(uInput) ==1:
            # Print history
            print('Input Log')
            print('{:-^40s}'.format('-'))
            for x in hist:
                print(x)
        case 'help' if len(uInput) ==1:
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
        case ('add' | 'buy') if len(uInput) > 1 and hasNum(uInput):     # Verify that command includes numbers
            print('Level 1')
            
            match uInput[1]:
                case 'item' if len(uInput) > 2 and uInput[2].isdigit() != True:    
                    print('Level 2')
                            
                    # Determine index of quantity in user input                
                    qInd = 0;
                    while uInput[qInd].isdigit() != True and qInd < len(uInput):
                        qInd += 1
                    print(qInd)
                    
                    # Get item name        
                    j = 2
                    itemName = uInput[j]   
                    while j < qInd-1:
                        j += 1
                    itemName = itemName + ' ' + uInput[j]
                
                    print(len(uInput[qInd:len(uInput)]))
                    print(len(uInput))
                    print(itemName)

                    match uInput[0]:
                        case 'add' if (len(uInput[qInd:len(uInput)]) == 2 and \
                                uInput[qInd+1][0] == '$' and isFloat(uInput[qInd+1][1:len(uInput[qInd+1])])):      
                            # Add item to inventory
                            quant[itemName] = int(uInput[qInd])
                            price[itemName] = float(uInput[qInd+1][1:len(uInput[qInd+1])])                            
                        case 'buy' if (len(uInput[qInd:len(uInput)]) == 5 and \
                                uInput[qInd+1].isdigit() and uInput[qInd+2].isdigit() and \
                                uInput[qInd+3].isdigit() and uInput[qInd+4].isdigit()):    
                            # Sell inventory items
                            print('Working on it')    
                        case other:
                            # Print error msg
                            print('Invalid syntax. Type \'help\' for more info.')
                case other:
                    # Print error msg
                    print('Invalid syntax. Type \'help\' for more info.')
        case other:
            # Print error msg
            print('Invalid command. Type \'help\' for more info.')

    print(quant)
    print(price)
    print(hist)
    print(len(uInput))
    for k in range(3):
        print('')
    

