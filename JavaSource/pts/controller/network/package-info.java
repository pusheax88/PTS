/**
 * Contains controllers of network entities. Each controller must have the interface,
 * must reference DAO for given model entity. The controller's name must end with Manager.
 * I.e. NetworkManager, DefaultNetworkManager. This is required for proper transaction 
 * management with Spring Aspect oriented programming.
 */
package pts.controller.network;