/**
 * SEED.GROOVY
 *
 * This file initializes the graph database with sample identity and address data.
 * It resets the graph, creates character and house vertices, and establishes relationships.
 *
 * To load this seed file using the Gremlin Console:
 *
 *   1. Start the Gremlin Console and connect to your graph database.
 *   2. Load this file with:
 *        :load scripts/seed.groovy
 *
 */
g.addV('house').property(T.id, 'house-stark-uuid').property('name', 'Stark').property('region', 'The North')
g.addV('character').property(T.id, 'eddard-stark-uuid').property('name', 'Eddard Stark').property('alias', 'Ned')
g.V('eddard-stark-uuid').as('a').V('house-stark-uuid').as('b').addE('member_of').from('a').to('b')
