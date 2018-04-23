nil = {'color': 'black'}


def makeNode(key, value, color='red'):
    return {'key': key,
            'value': value,
            'color': color,
            'parent': nil,
            'whatchild': '',
            'left': nil,
            'right': nil,
            'total': 0}


class RBTree:
    def __init__(self, k, v):
        self.root = makeNode(k, v, 'black')
        self.count = 1

    def add(self, k, v):
        x = self.root
        z = makeNode(k, v)
        while x != nil:
            if z['key'] < x['key']:
                z['parent'] = x
                x = x['left']
                z['whatchild'] = 'left'
            elif z['key'] > x['key']:
                z['parent'] = x
                x = x['right']
                z['whatchild'] = 'right'
            else:
                return '{} already in tree.'.format(z)
        x = z
        self.count += 1
        self.fix(x)

    def fix(self, node):
        if node['parent']['whatchild'] == 'left':
            side = 'right'
        else:
            side = 'left'
        # if red uncle
        if node['parent']['parent'][side] == 'red':
            node['parent']['parent'][side]['color'] = 'black'
            node['parent']['color'] = 'black'
        else:  # black uncle
            if node['whatchild'] == side:
                self.rotate(node)
                self.rotate(node)
                node['color'] = 'black'
                node[side]['color'] = 'red'
            else:
                self.rotate(node['parent'])
                node['parent']['color'] = 'black'
                node['parent'][side]['color'] = 'red'

    def rotate(self, toroot):
        if toroot == self.root:
            raise Exception
        fromroot = toroot['parent']
        if fromroot != self.root:
            superdescent = fromroot['whatchild']
        else:  # toroot is the new tree root
            superdescent = ''
            self.root = toroot
        if toroot['whatchild'] == 'left':
            direction = 'right'
            opposite = 'left'
        else:
            direction = 'left'
            opposite = 'right'
        toroot['parent'] = fromroot['parent']
        if self.root != toroot:
            fromroot['parent'][superdescent] = toroot
        toroot['whatchild'] = superdescent
        fromroot['parent'] = toroot
        fromroot['whatchild'] = direction
        toroot[direction]['parent'] = fromroot
        fromroot[opposite] = toroot[direction]
        fromroot[opposite]['whatchild'] = opposite
        toroot[direction] = fromroot
