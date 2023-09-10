# Print error msg
def errorMsg(msgType):
    match msgType:
        case 'noMoney':
            print('Error: No money for change in the machine.')
        case 'noInv':
            print('Error: Item is not in inventory.')
        case other:
            print('Error: Invalid syntax. Type \'help\' for more info.')

# Check string for decimal number
def isFloat(strNum):
    try:
        float(strNum)
        return True
    except ValueError:
        return False
    
# Verify whether a list of strings contains numbers
def hasNum(list):
    for x in list:
        if x.isdigit():
            return True
    return False

# Verify that 'add item' command has correct syntax and execute if correct
def verifyAdd(command):
    match command[1]:
        case 'item' if len(command) > 2 and command[2].isdigit() != True:    
            # Command has 'add item' and specifies 'item name'
                    
            # Determine index of quantity in user input                
            qInd = 0;
            while command[qInd].isdigit() != True and qInd < len(command):
                qInd += 1
            
            # Get item name        
            j = 2
            itemName = command[j]   
            while j < qInd-1:
                j += 1
                itemName = itemName + ' ' + command[j]

            # Verify there is nothing after price and ensure price has the right format
            if (len(command[qInd:len(command)]) == 2 and \
                command[qInd+1][0] == '$' and isFloat(command[qInd+1][1:len(command[qInd+1])])):      
                # Add item to inventory
                quant[itemName] = int(command[qInd])
                price[itemName] = float(command[qInd+1][1:len(command[qInd+1])])                            
            else:
                # Print error msg
                errorMsg()
        case other:
            # Print error msg
            errorMsg()

# Calculate and return the balance in cents
def calcBal(bal):
    return int(bal['dollar'])*100 + int(bal['quarter'])*25 + \
           int(bal['dime'])*10 + int(bal['nickel'])*5 + \
           int(bal['penny'])

# Calculate whether change can be made and return in number of dollars,
# quarters, dimes, nickels, and pennies
def calcChange(bal, changeC, changeS):
    changeS['dollar'] = changeC // 100      # Calc number of dollars
    changeC = changeC % 100                 # Calc remaining change
    changeS['quarter'] = changeC // 25      # Calc number of quarters
    changeC = changeC % 25                  # Calc remaining change
    changeS['dime'] = changeC // 10         # Calc number of dimes
    changeC = changeC % 10                  # Calc remaining change
    changeS['nickel'] = changeC // 5        # Calc number of nickels
    changeC = changeC % 5                   # Calc remaining change
    changeS['penny'] = changeC // 1         # Calc number of pennies

    return changeS

# Verify that 'buy item' command has correct syntax and execute if correct
def verifyBuy(command, bal, invQ, invP):
    match command[1]:
        case 'item' if len(command) > 2 and command[2].isdigit() != True:    
            # Command has 'buy item' and specifies 'item name'
            print('Level 2')
                    
            # Determine index of quantity in user input                
            qInd = 0;
            while command[qInd].isdigit() != True and qInd < len(command):
                qInd += 1
            print(qInd)
            
            # Get item name        
            j = 2
            itemName = command[j]   
            while j < qInd-1:
                j += 1
                itemName = itemName + ' ' + command[j]
        
            print(len(command[qInd:len(command)]))
            print(len(command))
            print(itemName)

            # Verify that the item exists
            if itemName in invQ and invQ[itemName] > 0:            
                # Verify there is nothing after specification of pennies and verify 
                # that specified quantities are integers            
                if (len(command[qInd:len(command)]) == 5 and \
                    command[qInd+1].isdigit() and command[qInd+2].isdigit() and \
                    command[qInd+3].isdigit() and command[qInd+4].isdigit()):    
                    # Sell inventory items
                    print('Working on it')    
                    # Calculate payment in cents
                    payC = int(command[qInd])*100 + int(command[qInd+1])*25 + \
                        int(command[qInd+2])*10 + int(command[qInd+3])*5 + \
                        int(command[qInd+4])
                    print(payC)

                    costC = int(invP[itemName]*100)
                    
                    # Check balance is greather than change required
                    changeC = payC - costC       # Calculate change in cents
                    print(changeC)

                    changeS = {     # Change in terms of dollars, quarters, dimes, nickels, and pennies
                        'dollar': 0,
                        'quarter': 0, 
                        'dime': 0, 
                        'nickel': 0,
                        'penny': 0 
                    }
                    balC = calcBal(bal)     # Current balance in machine
                    print(calcBal(bal))

                    if balC > changeC:
                        changeS = calcChange(balance, changeC, changeS)      # Calc denomination quantities
                        
                        print(changeS['dollar'])
                        print(changeS['quarter'])
                        print(changeS['dime'])
                        print(changeS['nickel'])
                        print(changeS['penny'])
                    else:
                        errorMsg('noMoney')
                else:
                    # Print error msg
                    errorMsg()
            else:
                # Print error msg
                errorMsg('noInv')
        case other:
            # Print error msg
            errorMsg()


##### Main program
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

while uInput[0] != 'exit' or len(uInput) != 1:
    # Read in user input and keep track of input history
    uInput = input('What do you want?: ')
    hist.append(uInput)
    uInput = uInput.split(' ')
    print('')

    match uInput[0]:
        case 'exit' if len(uInput) ==1:
            # Exit program
            print('{:-^40s}'.format('-'))
            print('Completing transaction...')
            print('{:-^40s}'.format('-'))
        case 'inventory' if len(uInput) ==1:
            # Print inventory
            print('{:-^85s}'.format('-'))
            print('{: ^25s} | {: ^25s} | {: ^25s}'.format('Item', 'Quantity', 'Price'))
            print('{:-^85s}'.format('-'))
            for x in quant:
                print('{: ^25s} | {: ^25d} | {: ^25s}'.format(x, quant[x], '$ '+str(price[x])))
        case 'balance' if len(uInput) ==1:
            # Print balance
            print('{:-^95s}'.format('-'))
            print('{: ^20s} | {: ^15s} | {: ^15s} | {: ^15s} | {: ^15s}'\
                .format('Dollar', 'Quarter', 'Dime', 'Nickel', 'Penny'))
            print('{:-^95s}'.format('-'))
            print('{: ^20} | {: ^15} | {: ^15} | {: ^15} | {: ^15}'\
                .format(balance['dollar'], balance['quarter'], balance['dime'], \
                balance['nickel'], balance['penny']))
        case 'history' if len(uInput) ==1:
            # Print history
            print('{:-^40s}'.format('-'))
            print('Input Log')
            print('{:-^40s}'.format('-'))
            for x in hist:
                print(x)
        case 'help' if len(uInput) ==1:
            # Print help msg
            print('{:-^115s}'.format('-'))
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
            print('{: <30s} | {: <25s} | {: <55s}'.format('buy item \{5\}', 'buy item chips 1 2 2 4 3', \
                'Buys an item with # dollars, quarters, dimes, nickles, ')) 
            print('{: <30s} | {: <25s} | {: <55s}'.format('', '', \
                'pennies. It also shows change given and the remaining '))
            print('{: <30s} | {: <25s} | {: <55s}'.format('', '', \
                'balance with currency distribution.  For change, the '))
            print('{: <30s} | {: <25s} | {: <55s}'.format('', '', \
                'machine  uses the largest denominator of curenncy that '))
            print('{: <30s} | {: <25s} | {: <55s}'.format('', '', \
                'is available.'))
            print('{:-^115s}'.format('-'))
            print('{: <30s} | {: <25s} | {: <25s}'.format('exit', 'exit', 'Exit the vending machine'))
            print('{:-^115s}'.format('-'))
        case 'add' if len(uInput) > 1 and hasNum(uInput):     # Verify that command includes numbers
            # Verify command format and execute
            verifyAdd(uInput)
        case 'buy' if len(uInput) > 1 and hasNum(uInput):     # Verify that command includes numbers
            # Verify command format and execute
            verifyBuy(uInput, balance, quant, price)
        case other:
            # Print error msg
            errorMsg()
            
    print(quant)
    print(price)
    print(hist)
    print(len(uInput))
    for k in range(3):
        print('')
    

