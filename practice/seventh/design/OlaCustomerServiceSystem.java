/**
 * <pre>
 * Design customer service system for Ola.
 * 
 * Requirements : User( can be driver or the customer ) should be able to raise a complaint. Each complaint has a 
 * priority associated with - LOW, MEDIUM, or HIGH.
 * 
 * There are three kinds of people at the customer service end. Call takers, Area manager, Head.
 * All the complaints are handled by call takers. But, medium level complaint has to be notified to area manger
 * and high level complaint has to be notified to Head as well in addition to Area manager.
 * 
 * Each category of complaint has a cap on the amount of time it is accepted to resolve. 
 * If it is not resolved by that time, it will be escalated to the next level. e.g. assume all low level complaints have to be
 * resolved with in 48 hours. If a complaint is not resolved with in that time, it's priority will be raised to medium level.
 * 
 * Assume the people at the customer service end have dashboard displaying complaints assigned to them. Assume a complaint allocation
 * system is already in-place to allocate complaints to people. Once a complaint is resolved, it has to disappear from the dashboard.
 * 
 * Design this.
 * </pre>
 * @author rebecca
 */
public class OlaCustomerServiceSystem {
	// Estimate the load
	
	// Availability is important, eventual consistency is good enough
	
	// Need to have multiple copies to read from so that listing is faster
	
	// Isolate data store that is being written to and data store that is being read from
	
	// High level component diagram
	
	// How data is organized in the data store ?
}
